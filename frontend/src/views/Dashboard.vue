<template>
  <div class="dashboard">
    <div class="card-grid">
      <Card title="插件下载量" color="#009688">
        <div class="card-body">
          <div class="stat-row">
            <StatTile :value="githubTotal" label="GitHub 下载" />
            <StatTile :value="npmTotal" label="npm 下载" />
          </div>
          <v-chart :option="downloadChartOption" style="height: 220px" autoresize />
        </div>
      </Card>

      <Card title="GitHub Star" color="#ff9800">
        <div class="card-body">
          <div class="stat-row">
            <StatTile :value="currentStars" label="当前 Star" />
          </div>
          <v-chart :option="starChartOption" style="height: 220px" autoresize />
        </div>
      </Card>

      <Card title="任务一次性完成率" color="#e91e63">
        <div class="card-body placeholder-box">
          <div class="placeholder-icon">P</div>
          <p class="placeholder-text">任务粒度定义待定（遗留问题 #1）</p>
          <p class="placeholder-sub">当前版本仅采集事件明细，完成率统计后续版本实现</p>
        </div>
      </Card>

      <Card title="测试场景覆盖" color="#3f51b5">
        <div class="card-body">
          <div class="stat-row">
            <StatTile :value="coverage.pass_count || 0" label="通过" />
            <StatTile :value="coverage.fail_count || 0" label="失败" />
          </div>
          <div class="progress-section">
            <ProgressBar label="Skills" :count="coverage.skill_count || 0" :maxCount="100" />
            <ProgressBar label="CLI" :count="coverage.cli_count || 0" :maxCount="200" />
            <ProgressBar label="API" :count="coverage.api_count || 0" :maxCount="150" />
            <ProgressBar label="SDK" :count="coverage.sdk_count || 0" :maxCount="80" />
          </div>
        </div>
      </Card>

      <Card title="VOD 反馈概览" color="#e91e63">
        <div class="card-body">
          <div class="stat-row">
            <StatTile :value="vodCounts.open" label="待处理" />
            <StatTile :value="vodCounts.acknowledged" label="已确认" />
            <StatTile :value="vodCounts.resolved" label="已解决" />
          </div>
        </div>
      </Card>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import VChart from 'vue-echarts'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { LineChart, BarChart } from 'echarts/charts'
import { GridComponent, TooltipComponent, LegendComponent } from 'echarts/components'
import Card from '../components/Card.vue'
import StatTile from '../components/StatTile.vue'
import ProgressBar from '../components/ProgressBar.vue'
import { fetchDownloads, fetchStars, fetchCoverage, fetchVodList } from '../api'

use([CanvasRenderer, LineChart, BarChart, GridComponent, TooltipComponent, LegendComponent])

const githubTotal = ref(0)
const npmTotal = ref(0)
const currentStars = ref(0)
const coverage = ref({})
const vodCounts = ref({ open: 0, acknowledged: 0, resolved: 0 })

const downloadDates = ref([])
const githubSeries = ref([])
const npmSeries = ref([])
const starDates = ref([])
const starSeries = ref([])

const downloadChartOption = computed(() => ({
  tooltip: { trigger: 'axis' },
  legend: {
    data: ['GitHub 下载', 'npm 下载'],
    bottom: 0,
    textStyle: { fontSize: 11 }
  },
  grid: { top: 10, right: 20, bottom: 30, left: 50 },
  xAxis: {
    type: 'category',
    data: downloadDates.value,
    axisLabel: { fontSize: 10, rotate: 30 }
  },
  yAxis: {
    type: 'value',
    axisLabel: { fontSize: 10 }
  },
  series: [
    {
      name: 'GitHub 下载',
      type: 'line',
      data: githubSeries.value,
      smooth: true,
      lineStyle: { color: '#009688', width: 2 },
      itemStyle: { color: '#009688' },
      symbol: 'none'
    },
    {
      name: 'npm 下载',
      type: 'line',
      data: npmSeries.value,
      smooth: true,
      lineStyle: { color: '#1a73e8', width: 2 },
      itemStyle: { color: '#1a73e8' },
      symbol: 'none'
    }
  ]
}))

const starChartOption = computed(() => ({
  tooltip: { trigger: 'axis' },
  grid: { top: 10, right: 20, bottom: 30, left: 50 },
  xAxis: {
    type: 'category',
    data: starDates.value,
    axisLabel: { fontSize: 10, rotate: 30 }
  },
  yAxis: {
    type: 'value',
    axisLabel: { fontSize: 10 }
  },
  series: [{
    name: 'Star',
    type: 'line',
    data: starSeries.value,
    smooth: true,
    lineStyle: { color: '#ff9800', width: 2 },
    areaStyle: { color: 'rgba(255, 152, 0, 0.08)' },
    itemStyle: { color: '#ff9800' },
    symbol: 'none'
  }]
}))

onMounted(async () => {
  try {
    const [dlRes, starRes, covRes, vodRes] = await Promise.all([
      fetchDownloads(),
      fetchStars(),
      fetchCoverage(),
      fetchVodList('open')
    ])

    const dlData = dlRes.data
    githubTotal.value = dlData.github_total || 0
    npmTotal.value = dlData.npm_total || 0
    githubSeries.value = (dlData.github_releases || []).map(p => p.value)
    npmSeries.value = (dlData.npm_downloads || []).map(p => p.value)
    downloadDates.value = (dlData.github_releases || []).map(p => {
      const d = new Date(p.date)
      return (d.getMonth() + 1) + '/' + d.getDate()
    })

    const starData = starRes.data
    currentStars.value = starData.current || 0
    starSeries.value = (starData.series || []).map(p => p.value)
    starDates.value = (starData.series || []).map(p => {
      const d = new Date(p.date)
      return (d.getMonth() + 1) + '/' + d.getDate()
    })

    coverage.value = covRes.data || {}

    const vodData = vodRes.data
    vodCounts.value = {
      open: vodData.open_count || 0,
      acknowledged: vodData.acknowledged_count || 0,
      resolved: vodData.resolved_count || 0
    }
  } catch (e) {
    console.error('Failed to load dashboard data:', e)
  }
})
</script>

<style scoped>
.card-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

@media (max-width: 768px) {
  .card-grid {
    grid-template-columns: 1fr;
  }
}

.card-body {
  min-height: 100px;
}

.stat-row {
  display: flex;
  justify-content: space-around;
  margin-bottom: 12px;
}

.progress-section {
  margin-top: 8px;
}

.placeholder-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 24px 0;
}

.placeholder-icon {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background: #fce4ec;
  color: #e91e63;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: 700;
  margin-bottom: 12px;
}

.placeholder-text {
  font-size: 14px;
  color: #888;
  text-align: center;
}

.placeholder-sub {
  font-size: 12px;
  color: #bbb;
  text-align: center;
  margin-top: 4px;
}
</style>
