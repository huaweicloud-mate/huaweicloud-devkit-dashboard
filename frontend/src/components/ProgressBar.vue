<template>
  <div class="progress-bar-wrap">
    <div class="progress-bar-label">
      <span>{{ label }}</span>
      <span>{{ count }}</span>
    </div>
    <div class="progress-bar-track">
      <div class="progress-bar-fill" :style="{ width: pct + '%' }" />
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  label: { type: String, default: '' },
  count: { type: Number, default: 0 },
  total: { type: Number, default: 100 },
  maxCount: { type: Number, default: 100 }
})

const pct = computed(() => {
  return Math.min(100, Math.round((props.count / props.maxCount) * 100))
})
</script>

<style scoped>
.progress-bar-wrap {
  margin-bottom: 14px;
}

.progress-bar-label {
  display: flex;
  justify-content: space-between;
  font-size: 13px;
  color: #666;
  margin-bottom: 6px;
}

.progress-bar-track {
  height: 10px;
  background: #e8ecf1;
  border-radius: 99px;
  overflow: hidden;
}

.progress-bar-fill {
  height: 100%;
  border-radius: 99px;
  background: linear-gradient(90deg, #1a73e8, #009688);
  transition: width 0.6s ease;
}
</style>
