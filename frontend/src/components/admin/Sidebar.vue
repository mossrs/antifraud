<template>
  <el-menu
    class="sidebar"
    vertical
    background-color="rgb(26, 34, 38)"
    text-color="#fff"
    active-text-color="#0097e6"
    default-active="/admin"
    @select="emitPageName"
    router
  >
    <el-menu-item index="/admin">
      <el-icon><Histogram /></el-icon>
      <template #title>概览</template>
    </el-menu-item>
    <el-menu-item index="/admin/user">
      <el-icon><UserFilled /></el-icon>
      <template #title>班级管理</template>
    </el-menu-item>
    <el-menu-item index="/admin/quiz">
      <el-icon><List /></el-icon>
      <template #title>题库管理</template>
    </el-menu-item>
    <el-menu-item index="/admin/setting">
      <el-icon><Tools /></el-icon>
      <template #title>系统设置</template>
    </el-menu-item>
  </el-menu>
</template>

<script setup lang="ts">
import { Histogram, UserFilled, List, Tools } from '@element-plus/icons-vue';

const emit = defineEmits(['select']);

const PAGENAME_MAP = new Map();
PAGENAME_MAP.set('/admin', ['概览', 'Dashboard']);
PAGENAME_MAP.set('/admin/user', ['班级管理', 'User']);
PAGENAME_MAP.set('/admin/quiz', ['题库管理', 'Quiz']);
PAGENAME_MAP.set('/admin/setting', ['系统设置', 'Setting']);

const emitPageName = (index) => {
  emit('select', PAGENAME_MAP.get(index));
};
</script>