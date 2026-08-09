<template>
  <div class="card" :style="{ borderTopColor: color }">
    <h2 class="card-title">{{ title }}</h2>
    <slot />
  </div>
</template>

<script setup>
defineProps({
  title: { type: String, required: true },
  color: { type: String, default: '#1a73e8' }
})
</script>

<style scoped>
.card {
  background: #fff;
  border: 1px solid #d9dee7;
  border-top: 4px solid #1a73e8;
  border-radius: 8px;
  padding: 20px 24px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.06);
  transition: box-shadow 0.2s;
}

.card:hover {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.card-title {
  font-size: 15px;
  font-weight: 600;
  color: #333;
  margin-bottom: 16px;
}
</style>
