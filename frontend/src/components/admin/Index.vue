<template>
  <el-container style="background-color: rgb(242,243,245)">
    <el-header class="nav">
      <Navbar />
    </el-header>
    <el-container>
      <el-aside style="overflow-y: hidden">
        <UserSide />
        <Sidebar @select="handleSelect" />
      </el-aside>
      <el-main>
          <router-view v-slot="{ Component }">
            <transition name="slide" mode="out-in">
                <div :key="pageName[0]" style="width: 100%; height: 100%;">
                  <div class="page-header">
                    <p>{{ pageName[0] }}</p>
                    <span>{{ pageName[1] }}</span>
                  </div>
                  <component :is="Component" @router-push="handleRouter" v-if="pageName[0] !== '概览'" />
                  <Dashboard v-else />
                </div>
            </transition>
          </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import Navbar from './Navbar.vue';
import UserSide from './UserSide.vue';
import Sidebar from './Sidebar.vue';
import Dashboard from './Dashboard.vue';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

import '../../style/admin/index.scss';

const pageName = ref(['概览', 'Dashboard']);
const router = useRouter();
const handleSelect = (val: string[]) => pageName.value = val;
const handleRouter = (path: string) => router.push(path);
</script>