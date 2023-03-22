<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useAuthorization, useRouterTransition } from './hooks';

import './style/index.scss';

const router = useRouter();
const routerTransitionAnimation = useRouterTransition(router);

const handleRouter = (path: string) => {
  router.push(path);
};

useAuthorization(router);

</script>

<template>
  <!-- navbar -->

  <!-- router-view -->
  <router-view v-slot="{ Component }">
    <transition :name="routerTransitionAnimation">
      <keep-alive>
          <component class="container" :is="Component" @router-push="handleRouter" />
      </keep-alive>
    </transition>
  </router-view>
</template>

<style>
#app {
  font-family: var(--el-font-family);
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0;
  padding: 0;
}

body {
  position: absolute;
  overflow: hidden;
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  font-family: var(--el-font-family);
}

.container {
  position: absolute;
  width: 100%;
  height: 100%;
}

.slide-up-enter-from {
  transform: translateY(100%);
  opacity: 0;
}

.slide-up-leave-to {
  transform: translateY(-100%);
  opacity: 0;
}

.slide-up-enter-to, .slide-up-leave-from {
  transform: translateY(0);
  opacity: 1;
}

.slide-down-enter-from {
  transform: translateY(-100%);
  opacity: 0;
}

.slide-down-leave-to {
  transform: translateY(100%);
  opacity: 0;
}

.slide-down-enter-to, .slide-down-leave-from {
  transform: translateY(0);
  opacity: 1;
}

.slide-up-enter-active,
.slide-up-leave-active,
.slide-down-enter-active,
.slide-down-leave-active {
  transition: transform .7s cubic-bezier(0.23,1,0.32,1), opacity .5s ease;
}
</style>
