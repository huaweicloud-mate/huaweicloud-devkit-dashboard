<template>
  <div class="vod-page">
    <Card title="VOD 开放能力缺陷反馈" color="#e91e63">
      <div class="stats-bar">
        <div class="stat-item">
          <span class="stat-num open">{{ counts.open }}</span>
          <span class="stat-desc">待处理</span>
        </div>
        <div class="stat-item">
          <span class="stat-num acknowledged">{{ counts.acknowledged }}</span>
          <span class="stat-desc">已确认</span>
        </div>
        <div class="stat-item">
          <span class="stat-num resolved">{{ counts.resolved }}</span>
          <span class="stat-desc">已解决</span>
        </div>
      </div>

      <div class="filter-bar">
        <button
          v-for="s in ['open', 'acknowledged', 'resolved']"
          :key="s"
          :class="['filter-btn', { active: filter === s }]"
          @click="filter = s; loadList()"
        >{{ statusLabel(s) }}</button>
      </div>

      <table class="data-table" v-if="feedbacks.length">
        <thead>
          <tr>
            <th>服务</th>
            <th>操作</th>
            <th>问题类型</th>
            <th>描述</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="f in feedbacks" :key="f.id">
            <td>{{ f.serviceName || '-' }}</td>
            <td>{{ f.operationName || '-' }}</td>
            <td><span class="badge">{{ issueTypeLabel(f.issueType) }}</span></td>
            <td class="desc-cell">{{ f.issueDesc || '-' }}</td>
            <td><span :class="'badge badge-' + f.status">{{ statusLabel(f.status) }}</span></td>
            <td class="action-cell">
              <select v-model="statusChanges[f.id]" class="status-select">
                <option value="">变更状态</option>
                <option value="acknowledged">已确认</option>
                <option value="resolved">已解决</option>
              </select>
              <button class="btn-sm btn-primary" @click="changeStatus(f)" :disabled="!statusChanges[f.id]">更新</button>
            </td>
          </tr>
        </tbody>
      </table>
      <p v-else class="empty-text">暂无反馈数据</p>
    </Card>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import Card from '../components/Card.vue'
import { fetchVodList, updateVodStatus } from '../api'

const feedbacks = ref([])
const counts = ref({ open: 0, acknowledged: 0, resolved: 0 })
const filter = ref('open')
const statusChanges = reactive({})

function statusLabel(s) {
  const map = { open: '待处理', acknowledged: '已确认', resolved: '已解决' }
  return map[s] || s
}

function issueTypeLabel(t) {
  const map = { api_bug: 'API Bug', doc_gap: '文档缺失', missing_capability: '能力缺失' }
  return map[t] || t
}

async function loadList() {
  try {
    const res = await fetchVodList(filter.value)
    const data = res.data
    feedbacks.value = data.feedbacks || []
    counts.value = {
      open: data.open_count || 0,
      acknowledged: data.acknowledged_count || 0,
      resolved: data.resolved_count || 0
    }
  } catch (e) {
    console.error('Failed to load VOD list:', e)
  }
}

async function changeStatus(f) {
  const newStatus = statusChanges[f.id]
  if (!newStatus) return
  try {
    await updateVodStatus({ id: f.id, status: newStatus })
    statusChanges[f.id] = ''
    await loadList()
  } catch (e) {
    console.error('Failed to update status:', e)
  }
}

onMounted(loadList)
</script>

<style scoped>
.stats-bar {
  display: flex;
  gap: 32px;
  margin-bottom: 20px;
  padding-bottom: 16px;
  border-bottom: 1px solid #f0f1f3;
}

.stat-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 4px;
}

.stat-num {
  font-size: 26px;
  font-weight: 760;
}
.stat-num.open { color: #1565c0; }
.stat-num.acknowledged { color: #e65100; }
.stat-num.resolved { color: #2e7d32; }

.stat-desc {
  font-size: 12px;
  color: #888;
}

.filter-bar {
  display: flex;
  gap: 8px;
  margin-bottom: 16px;
}

.filter-btn {
  padding: 5px 14px;
  border-radius: 14px;
  border: 1px solid #d9dee7;
  background: #fff;
  font-size: 12px;
  cursor: pointer;
  color: #666;
  transition: all 0.15s;
}

.filter-btn.active {
  background: #e91e63;
  color: #fff;
  border-color: #e91e63;
}

.filter-btn:hover:not(.active) {
  border-color: #bbb;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 13px;
}

.data-table th {
  text-align: left;
  padding: 10px 12px;
  border-bottom: 2px solid #e5e7eb;
  color: #666;
  font-weight: 600;
  font-size: 12px;
  text-transform: uppercase;
}

.data-table td {
  padding: 10px 12px;
  border-bottom: 1px solid #f0f1f3;
  color: #333;
}

.desc-cell {
  max-width: 260px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.badge {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 10px;
  font-size: 11px;
  font-weight: 500;
}

.badge-open { background: #e3f2fd; color: #1565c0; }
.badge-acknowledged { background: #fff3e0; color: #e65100; }
.badge-resolved { background: #e8f5e9; color: #2e7d32; }

.action-cell {
  white-space: nowrap;
}

.status-select {
  padding: 4px 8px;
  border: 1px solid #d9dee7;
  border-radius: 4px;
  font-size: 12px;
  margin-right: 6px;
}

.btn-sm {
  padding: 4px 10px;
  font-size: 12px;
  border-radius: 4px;
  border: 1px solid #d9dee7;
  background: #fff;
  cursor: pointer;
}

.btn-primary {
  background: #1a73e8;
  color: #fff;
  border-color: #1a73e8;
}

.btn-primary:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.btn-primary:hover:not(:disabled) {
  background: #1557b0;
}

.empty-text {
  text-align: center;
  color: #bbb;
  padding: 40px 0;
  font-size: 14px;
}
</style>
