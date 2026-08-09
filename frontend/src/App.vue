<template>
  <div id="app-shell">
    <header class="masthead">
      <div class="masthead-inner">
        <h1>华为云 DevKit 运营看板</h1>
        <span class="masthead-time">生成时间：{{ now }}</span>
      </div>
    </header>
    <nav class="topnav">
      <router-link to="/">看板总览</router-link>
      <router-link to="/scenarios">测试场景</router-link>
      <router-link to="/vod">VOD 反馈</router-link>
    </nav>
    <main class="main-content">
      <router-view />
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'

const now = ref('')
let timer = null

function updateTime() {
  const d = new Date()
  now.value = d.toLocaleString('zh-CN', { hour12: false })
}

onMounted(() => {
  updateTime()
  timer = setInterval(updateTime, 1000)
})

onBeforeUnmount(() => {
  if (timer) clearInterval(timer)
})
</script>

<style>
#app-shell {
  max-width: 1180px;
  margin: 0 auto;
  padding: 0 20px 40px;
}

.masthead {
  padding: 28px 0 16px;
  border-bottom: 1px solid #e5e7eb;
  margin-bottom: 20px;
}

.masthead-inner {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
}

.masthead h1 {
  font-size: 22px;
  font-weight: 700;
  color: #1a1a2e;
}

.masthead-time {
  font-size: 13px;
  color: #999;
}

.topnav {
  display: flex;
  gap: 0;
  margin-bottom: 24px;
  border-bottom: 2px solid #e5e7eb;
}

.topnav a {
  padding: 10px 24px;
  font-size: 14px;
  font-weight: 500;
  color: #666;
  text-decoration: none;
  border-bottom: 2px solid transparent;
  margin-bottom: -2px;
  transition: color 0.2s, border-color 0.2s;
}

.topnav a:hover {
  color: #333;
}

.topnav a.router-link-exact-active {
  color: #1a73e8;
  border-bottom-color: #1a73e8;
}

.main-content {
  min-height: 400px;
}
</style>
