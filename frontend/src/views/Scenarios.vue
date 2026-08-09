<template>
  <div class="scenarios-page">
    <Card title="测试场景管理" color="#3f51b5">
      <div class="toolbar">
        <button class="btn btn-primary" @click="showForm = true">+ 新增场景</button>
        <span class="toolbar-info">{{ scenarios.length }} 个场景</span>
      </div>

      <div v-if="showForm" class="form-card">
        <h3>{{ editing ? '编辑场景' : '新增场景' }}</h3>
        <div class="form-grid">
          <div class="form-field">
            <label>场景名称 *</label>
            <input v-model="form.scenario_name" placeholder="例如: ECS Create Instance" />
          </div>
          <div class="form-field">
            <label>Skill 名称</label>
            <input v-model="form.skill_name" placeholder="例如: huaweicloud-ecs" />
          </div>
          <div class="form-field">
            <label>CLI 服务</label>
            <input v-model="form.cli_service" placeholder="例如: ECS" />
          </div>
          <div class="form-field">
            <label>CLI 操作</label>
            <input v-model="form.cli_operation" placeholder="例如: CreateServers" />
          </div>
          <div class="form-field">
            <label>API 路径</label>
            <input v-model="form.api_path" placeholder="例如: /v1/{project_id}/cloudservers" />
          </div>
          <div class="form-field">
            <label>API 方法</label>
            <select v-model="form.api_method">
              <option value="">--</option>
              <option value="GET">GET</option>
              <option value="POST">POST</option>
              <option value="PUT">PUT</option>
              <option value="DELETE">DELETE</option>
            </select>
          </div>
          <div class="form-field">
            <label>SDK 语言</label>
            <select v-model="form.sdk_language">
              <option value="">--</option>
              <option value="Python">Python</option>
              <option value="Java">Java</option>
              <option value="Go">Go</option>
              <option value="Node.js">Node.js</option>
            </select>
          </div>
          <div class="form-field">
            <label>状态</label>
            <select v-model="form.status">
              <option value="active">active</option>
              <option value="archived">archived</option>
            </select>
          </div>
          <div class="form-field">
            <label>测试结果</label>
            <select v-model="form.last_result">
              <option value="untested">untested</option>
              <option value="pass">pass</option>
              <option value="fail">fail</option>
            </select>
          </div>
        </div>
        <div class="form-actions">
          <button class="btn btn-primary" @click="saveScenario">{{ editing ? '更新' : '保存' }}</button>
          <button class="btn btn-ghost" @click="resetForm">取消</button>
        </div>
      </div>

      <table class="data-table" v-if="scenarios.length">
        <thead>
          <tr>
            <th>场景名称</th>
            <th>Skill</th>
            <th>CLI</th>
            <th>API</th>
            <th>SDK</th>
            <th>状态</th>
            <th>结果</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="s in scenarios" :key="s.id">
            <td>{{ s.scenarioName }}</td>
            <td>{{ s.skillName || '-' }}</td>
            <td>{{ s.cliService ? s.cliService + '/' + s.cliOperation : '-' }}</td>
            <td>{{ s.apiMethod ? s.apiMethod + ' ' + s.apiPath : '-' }}</td>
            <td>{{ s.sdkLanguage || '-' }}</td>
            <td><span :class="'badge badge-' + s.status">{{ s.status }}</span></td>
            <td><span :class="'badge badge-' + (s.lastResult || 'untested')">{{ s.lastResult || 'untested' }}</span></td>
            <td class="action-cell">
              <button class="btn-sm" @click="editScenario(s)">编辑</button>
              <button class="btn-sm btn-danger" @click="removeScenario(s.id)">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
      <p v-else class="empty-text">暂无场景数据</p>
    </Card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Card from '../components/Card.vue'
import { fetchScenarios, addScenario, updateScenario, deleteScenario } from '../api'

const scenarios = ref([])
const showForm = ref(false)
const editing = ref(false)

const emptyForm = () => ({
  scenario_name: '',
  skill_name: '',
  cli_service: '',
  cli_operation: '',
  api_path: '',
  api_method: '',
  sdk_language: '',
  status: 'active',
  last_result: 'untested'
})

const form = ref(emptyForm())

async function loadScenarios() {
  try {
    const res = await fetchScenarios()
    scenarios.value = res.data.scenarios || []
  } catch (e) {
    console.error('Failed to load scenarios:', e)
  }
}

function editScenario(s) {
  editing.value = true
  form.value = {
    id: s.id,
    scenario_name: s.scenarioName,
    skill_name: s.skillName || '',
    cli_service: s.cliService || '',
    cli_operation: s.cliOperation || '',
    api_path: s.apiPath || '',
    api_method: s.apiMethod || '',
    sdk_language: s.sdkLanguage || '',
    status: s.status || 'active',
    last_result: s.lastResult || 'untested'
  }
  showForm.value = true
}

function resetForm() {
  editing.value = false
  form.value = emptyForm()
  showForm.value = false
}

async function saveScenario() {
  try {
    if (editing.value) {
      await updateScenario(form.value)
    } else {
      await addScenario(form.value)
    }
    resetForm()
    await loadScenarios()
  } catch (e) {
    console.error('Failed to save scenario:', e)
  }
}

async function removeScenario(id) {
  if (!confirm('确定删除此场景？')) return
  try {
    await deleteScenario({ id })
    await loadScenarios()
  } catch (e) {
    console.error('Failed to delete scenario:', e)
  }
}

onMounted(loadScenarios)
</script>

<style scoped>
.toolbar {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 16px;
}

.toolbar-info {
  font-size: 13px;
  color: #888;
}

.btn {
  padding: 8px 16px;
  border-radius: 6px;
  font-size: 13px;
  font-weight: 500;
  cursor: pointer;
  border: 1px solid #d9dee7;
  background: #fff;
  color: #333;
  transition: all 0.15s;
}

.btn-primary {
  background: #1a73e8;
  color: #fff;
  border-color: #1a73e8;
}

.btn-primary:hover {
  background: #1557b0;
}

.btn-ghost {
  background: transparent;
  border-color: transparent;
  color: #666;
}

.btn-ghost:hover {
  color: #333;
  background: #f5f5f5;
}

.btn-sm {
  padding: 4px 10px;
  font-size: 12px;
  border-radius: 4px;
  border: 1px solid #d9dee7;
  background: #fff;
  cursor: pointer;
  margin-right: 4px;
}

.btn-sm:hover { background: #f5f5f5; }
.btn-danger { color: #e91e63; border-color: #e91e63; }
.btn-danger:hover { background: #fce4ec; }

.form-card {
  background: #fafbfc;
  border: 1px solid #e5e7eb;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 20px;
}

.form-card h3 {
  font-size: 15px;
  font-weight: 600;
  margin-bottom: 16px;
  color: #333;
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 12px 16px;
}

.form-field {
  display: flex;
  flex-direction: column;
}

.form-field label {
  font-size: 12px;
  color: #666;
  margin-bottom: 4px;
}

.form-field input,
.form-field select {
  padding: 7px 10px;
  border: 1px solid #d9dee7;
  border-radius: 6px;
  font-size: 13px;
  outline: none;
}

.form-field input:focus,
.form-field select:focus {
  border-color: #1a73e8;
  box-shadow: 0 0 0 2px rgba(26, 115, 232, 0.1);
}

.form-actions {
  margin-top: 16px;
  display: flex;
  gap: 10px;
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

.badge {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 10px;
  font-size: 11px;
  font-weight: 500;
}

.badge-active { background: #e8f5e9; color: #2e7d32; }
.badge-archived { background: #f5f5f5; color: #888; }
.badge-pass { background: #e8f5e9; color: #2e7d32; }
.badge-fail { background: #fce4ec; color: #c62828; }
.badge-untested { background: #fff3e0; color: #e65100; }
.badge-open { background: #e3f2fd; color: #1565c0; }
.badge-acknowledged { background: #fff3e0; color: #e65100; }
.badge-resolved { background: #e8f5e9; color: #2e7d32; }

.action-cell {
  white-space: nowrap;
}

.empty-text {
  text-align: center;
  color: #bbb;
  padding: 40px 0;
  font-size: 14px;
}
</style>
