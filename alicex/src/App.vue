<template>
  <div id="app">
    <mu-appbar title="Alice - 聆听这个世界">
      <mu-icon-button icon="menu" slot="left" @click="toggle(true)"></mu-icon-button>
      <mu-text-field icon="search" slot="right" hintText="搜索音乐" class="appbar-search-field"></mu-text-field>
      <mu-flat-button label="播放列表" slot="right"></mu-flat-button>
      <mu-flat-button label="我喜欢的音乐" slot="right"></mu-flat-button>
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
        <h1 id="pickname" class="pickname-field" align="center">Juhezi</h1>
        <br/>
        <mu-raised-button id="sign_in_out" label="退出登录" fullWidth primary></mu-raised-button>
      </div>
    </mu-drawer>
    <!--中央控制模块-->
    <div align="center" class="fill_main">
      <div align="center" class="play-ctrl">
        <mu-card>
          <mu-card-title title="刚好遇见你" subTitle="李玉刚"></mu-card-title>
          <mu-card-actions>
            <mu-flat-button label="Action 1"></mu-flat-button>
            <mu-flat-button label="Action 2"></mu-flat-button>
          </mu-card-actions>
        </mu-card>
      </div>
    </div>
  </div>
</template>

<script>
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
  export default {
    components: {
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
      //初始值
      return {
        dialog: false,
        open: false,
        docked: true
      }
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
      }
    }
  }
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
    text-align: center;
    color: #2c3e50;
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

  .fill_main {
    background: #00f;
    width: 100%;
  }


</style>
