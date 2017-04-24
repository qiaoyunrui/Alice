<template>
  <mu-appbar title="Alice - 聆听这个世界">
    <mu-icon-button icon="menu" slot="left"></mu-icon-button>
    <mu-text-field icon="search" slot="right" hintText="搜索音乐"></mu-text-field>
    <mu-flat-button label="播放列表" slot="right"></mu-flat-button>
    <mu-flat-button href="333" label="我喜欢的音乐" slot="right"></mu-flat-button>
    <mu-icon-menu slot="right" icon="more_vert">
      <mu-menu-item title="关于我们"></mu-menu-item>
      <mu-menu-item title="Github" href="https://github.com/qiaoyunrui/Alice"></mu-menu-item>
    </mu-icon-menu>
  </mu-appbar>
</template>

<!--<style lang="less">
  .appbar-search-field {
    color: #FFF;
    margin-bottom: 0;
    &.focus-state {
      color: #FFF;
    }
    .mu-text-field-hint {
      color: fade(#FFF, 54%);
    }
    .mu-text-field-input {
      color: #FFF;
    }
    .mu-text-field-focus-line {
      background-color: #FFF;
    }
  }
</style>-->
