<template>
  <transition name="router-fade" mode="out-in">
    <router-view></router-view>
  </transition>
  <!--<img id="loading" src="../static/img/timg.gif" />-->
</template>
<style>
  /*#loading{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    z-index: 999;
    display: none;
  }*/
</style>