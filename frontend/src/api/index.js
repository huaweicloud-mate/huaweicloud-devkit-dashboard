import axios from 'axios'

const BASE = '/rest/developer/server/hcdevkitportal/v1'

const api = axios.create({
  baseURL: BASE,
  timeout: 15000,
  headers: { 'Content-Type': 'application/json' }
})

export function fetchDownloads() {
  return api.get('/metrics/downloads')
}

export function fetchStars() {
  return api.get('/metrics/stars')
}

export function fetchCoverage() {
  return api.get('/metrics/coverage')
}

export function fetchScenarios(status = 'active') {
  return api.get('/scenarios/list', { params: { status } })
}

export function addScenario(data) {
  return api.post('/scenarios/add', data)
}

export function updateScenario(data) {
  return api.post('/scenarios/update', data)
}

export function deleteScenario(data) {
  return api.post('/scenarios/delete', data)
}

export function fetchVodList(status = 'open') {
  return api.get('/vod/list', { params: { status } })
}

export function updateVodStatus(data) {
  return api.post('/vod/updateStatus', data)
}

export default api
