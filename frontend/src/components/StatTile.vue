<template>
  <div class="stat-tile">
    <div class="stat-value">{{ formatted }}</div>
    <div class="stat-label">{{ label }}</div>
  </div>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  value: { type: Number, default: 0 },
  label: { type: String, default: '' }
})

const formatted = computed(() => {
  if (props.value >= 10000) {
    return (props.value / 1000).toFixed(1) + 'k'
  }
  return props.value.toLocaleString()
})
</script>

<style scoped>
.stat-tile {
  text-align: center;
  padding: 12px 16px;
}

.stat-value {
  font-size: 28px;
  font-weight: 760;
  color: #1a1a2e;
  line-height: 1.2;
}

.stat-label {
  font-size: 13px;
  color: #888;
  margin-top: 4px;
}
</style>
