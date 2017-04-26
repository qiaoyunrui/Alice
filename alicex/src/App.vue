<template>
  <div id="app">
    <mu-appbar title="Alice - 聆听这个世界">
      <mu-icon-button icon="menu" slot="left" @click="toggle(true)"></mu-icon-button>
      <mu-text-field icon="search" slot="right" hintText="搜索音乐" v-model="name"
                     class="appbar-search-field"></mu-text-field>
      <mu-flat-button label="搜索" slot="right" @click="search"></mu-flat-button>
      <mu-flat-button label="播放列表" slot="right" @click="openSongList"></mu-flat-button>
      <mu-icon-menu slot="right" icon="more_vert">
        <mu-menu-item title="关于我们" @click="openX"></mu-menu-item>
        <mu-menu-item title="Github" href="https://github.com/qiaoyunrui/Alice"></mu-menu-item>
      </mu-icon-menu>
    </mu-appbar>
    <!--团队信息对话框-->
    <mu-dialog :open="dialog" title="团队信息" @close="close">
      <h2>NUC-Alice-Group</h2><br/>
      <mu-list>
        <mu-list-item title="Juhezi" href="https://github.com/qiaoyunrui"></mu-list-item>
        <mu-list-item title="SkyLoong" href="https://github.com/skyloong"></mu-list-item>
        <mu-list-item title="Feng"></mu-list-item>
        <mu-list-item title="Cheng"></mu-list-item>
      </mu-list>
      <mu-flat-button slot="actions" primary @click="closeX" label="确定"></mu-flat-button>
    </mu-dialog>
    <!--左侧弹出菜单-->
    <mu-drawer :open="open" :docked="docked" @close="toggle()">
      <div class="container">
        <h1 id="pickname" class="pickname-field" align="center">{{username}}</h1>
        <br/>
        <mu-raised-button id="sign_in_out" :label="isOnline ? '退出登录':'登录'" fullWidth primary
                          @click="sign_operate()"></mu-raised-button>
      </div>
    </mu-drawer>
    <!--页面内容-->
    <div align="center" id="content">
      <!--歌曲控制模块-->
      <div align="center" class="play-ctrl">
        <mu-card>
          <mu-card-media>
            <img src="./assets/images/background.jpg"/>
          </mu-card-media>
          <mu-card-title :title="currentSong.name" :subTitle="currentSong.author"></mu-card-title>
          <mu-card-actions>
            <mu-flat-button icon="skip_previous" @click="prev"></mu-flat-button>
            <mu-flat-button :icon="isplaying ? 'pause' : 'play_arrow'" @click="changePlayState"></mu-flat-button>
            <mu-flat-button icon="skip_next" @click="next"></mu-flat-button>
          </mu-card-actions>
        </mu-card>
      </div>
    </div>
    <!--搜索信息对话框-->
    <div>
      <mu-dialog :open="findResult" @close="" title="搜索结果" scrollable>
        <mu-list>
          <mu-list-item :title="song.name" :describeText="song.author" v-for="(song,index) in findList" :key="index"
                        disabled>
            <mu-avatar backgroundColor="blue" slot="leftAvatar">{{index}}</mu-avatar>
            <mu-icon-button icon="play_arrow" slot="rightAvatar" tooltip="播放" class="center"
                            @click="playSong(song,-1)"></mu-icon-button>
            <mu-icon-button icon="add" tooltip="添加到播放列表" @click="addSong(song.id)"></mu-icon-button>
            <mu-icon-button icon="more_horiz" tooltip="更多"></mu-icon-button>
          </mu-list-item>
        </mu-list>
        <mu-flat-button primary label="关闭" @click="hideFindResult" slot="actions"></mu-flat-button>
      </mu-dialog>
    </div>
    <!--播放列表-->
    <div>
      <mu-drawer right :open="songList">
        <mu-appbar title="播放列表"></mu-appbar>
        <mu-flat-button icon="close" label="关闭" secondary class="match-parent"
                        @click="songList = false"></mu-flat-button>
        <mu-list>
          <mu-list-item :title="song.name" :describeText="song.author" v-for="(song,index) in songs" :key="index"
                        disabled>
            <mu-icon-button icon="play_arrow" tooltip="播放" class="center"
                            @click="playSong(song,index)"></mu-icon-button>
            <mu-icon-button icon="delete" tooltip="删除" class="center"
                            @click="deleteSong(index)"></mu-icon-button>
          </mu-list-item>
        </mu-list>
      </mu-drawer>
    </div>
  </div>
</template>

<script>
  var audios = document.createElement('audio');
  //插入音乐标签
  document.body.appendChild(audios)
  function Song(id, name, author, path) {
    this.id = id
    this.name = name
    this.author = author
    this.path = path
  }
  import MuListItem from "../node_modules/muse-ui/src/list/listItem";
  import MuDrawer from "../node_modules/muse-ui/src/drawer/drawer";
  import MuCardText from "../node_modules/muse-ui/src/card/cardText";
  import MuFlatButton from "../node_modules/muse-ui/src/flatButton/flatButton";
  import MuRaisedButton from "../node_modules/muse-ui/src/raisedButton/raisedButton";
  import MuCard from "../node_modules/muse-ui/src/card/card";
  import MuCardHeader from "../node_modules/muse-ui/src/card/cardHeader";
  import MuAvatar from "../node_modules/muse-ui/src/avatar/avatar";
  import MuCardMedia from "../node_modules/muse-ui/src/card/cardMedia";
  import MuCardTitle from "../node_modules/muse-ui/src/card/cardTitle";
  import $ from 'jquery';
  import MuIconButton from "../node_modules/muse-ui/src/iconButton/iconButton";
  export default {
    components: {
      MuIconButton,
      MuCardTitle,
      MuCardMedia,
      MuAvatar,
      MuCardHeader,
      MuCard,
      MuRaisedButton,
      MuFlatButton,
      MuCardText,
      MuDrawer,
      MuListItem
    },
    data () {
      const findList = [] //搜索结果
      const songs = []  //当前播放列表
      //初始值
      return {
        dialog: false,
        open: false,
        docked: true,
        currentSong: new Song('000', '刚好遇到你', '李玉刚', '../static/test.mp3'),
        username: '---',
        user_operate: '登录',
        isOnline: false,
        isplaying: false,
        name: "薛之谦",
        findResult: false,
        findList,
        songList: false, //播放列表
        songs,
        index: -1
      }
    },
    created: function () {
      audios.src = this.currentSong.path
      this.getUserInfo()  //进入界面就进行判断
    },
    methods: {
      openX() {
        this.dialog = true
      },
      closeX() {
        this.dialog = false
      },
      toggle (flag) {
        this.open = !this.open
        this.docked = !flag
      },
      sign_operate() {  //登录或者登录逻辑判断
        if (!this.isOnline) {
          this.sign_in()
        } else {
          this.sign_out()
        }
      },
      sign_in() {
        window.location.href = 'http://localhost:8090/sign_in_up/view/login.html' //跳转到登录界面,
      },
      sign_out() {
        document.cookie = "online=false; alice_username=" + this.username
        this.isOnline = false
        this.username = '---'
      },
      changePlayState() {
        this.isplaying = !this.isplaying
        if (this.isplaying) {  //正在播放
          this.playX();
        } else {
          this.pauseX();
        }
      },
      playX() { //开始播放
        audios.play()
      },
      pauseX() {  //暂停播放
        audios.pause()
      },
      showFindResult() {
        this.findResult = true
      },
      hideFindResult() {
        this.findResult = false
      },
      search() {
        //name 为关键字，搜索
        this.findList = []
        var url = "http://localhost:8090/SongServlet?method=findByName&name=" + this.name
        var _self = this
        $.get(url, function (data) {
          var list = eval(data)
          for (let i = 0; i < list.length; i++) {
            _self.findList.push(new Song(list[i].id, list[i].name, list[i].author, list[i].path))
          }
          _self.showFindResult()
        })
      },
      playSong(song, index) {
        audios.src = song.path
        this.currentSong = song
        audios.play()
        this.isplaying = true
        this.index = index
      },
      openSongList() {
        //判断是否登录
        if (this.isOnline) {
          this.getSongList()
        }
      },
      getUserInfo() {  //获取登录状态
        var url = window.location.toString()
        var get = url.split("?");
        if (get.length === 2) { //get 方式
          var cok = get[1]
          document.cookie = get[1]; //保存 cookie
        } else {  //从 cookie 中获取数据，解析cookie
          var coks = document.cookie.split("; ")
          var line1 = coks[0].split("=")
          var line2 = coks[1].split("=")
          if (line1[0] === "online") {
            this.isOnline = line1[1]
            this.username = line2[1]
          } else {
            this.isOnline = line2[1]
            this.username = line1[1]
          }
        }
      },
      addSong(songid) { //添加歌曲到播放列表
        var url = "http://localhost:8090/NSongServet?method=add&songid=" + songid + "&userid=" + this.username
        $.get(url, function (data) {
        })
      },
      getSongList() { //获取播放列表
        this.songs = []
        var url = "http://localhost:8090/NSongServet?method=findAll&userid=" + this.username
        var _self = this
        $.get(url, function (data) {
          var list = eval(data)
          for (let i = 0; i < list.length; i++) {
            _self.songs.push(new Song(list[i].songId, list[i].songName, list[i].author, list[i].path))
          }
          _self.songList = true
        })
      },
      deleteSong(index) {
        var url = "http://localhost:8090/NSongServet?method=delete&songid=" + this.songs[index].id + "&userid=" + this.username
        var _self = this
        $.get(url, function (data) {
          _self.songs.splice(index)
        })
      },
      next() { //下一首
        if (this.index == -1 || this.index + 1 >= this.songs.length) {
          this.playSong(this.songs[0], 0)
          index = 0
        } else {
          this.index++
          this.playSong(this.songs[this.index], this.index)
        }
      },
      prev() { //上一首
        if (this.index <= 0 || this.index - 1 >= this.songs.length) {
          this.playSong(this.songs[this.songs.length - 1], this.songs.length - 1)
          index = 0
        } else {
          this.index--
          this.playSong(this.songs[this.index], this.index)
        }
      }
    }
  }
  //获取用户登录信息，及用户名
</script>

<style lang="less">
  .appbar-search-field {
    color: #FFF;
    margin-bottom: 0;
    &.focus-state {
      color: #FFF;
    }
    .mu-text-field-hint {
      color: fade(#FFF, 50%);
    }
    .mu-text-field-input {
      color: #FFF;
    }
    .mu-text-field-focus-line {
      background-color: #FFF;
    }
  }

</style>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  #content {
    margin-top: 100px;
    margin-bottom: 100px;
  }

  body {
    background-color: #0ff;
  }

  .container {
    padding: 12px;
  }

  .pickname-field {
    color: #f0f;
  }

  .play-ctrl {
    width: 500px;
  }

</style>

<style scoped>
  .center {
    margin-top: auto;
    margin-bottom: auto;
  }

  .match-parent {
    width: 100%;
    margin: 5px;
  }
</style>
