// src/store/modules/drone.js
const state = {
  pitch: 0,
  roll: 0,
  yaw: 0,
  latitude: 0,
  longitude: 0,
  altitude: 0
}

const mutations = {
  updateDroneData(state, payload) {
    state.pitch = payload.pitch
    state.roll = payload.roll
    state.yaw = payload.yaw
    state.latitude = payload.latitude
    state.longitude = payload.longitude
    state.altitude = payload.altitude
  }
}

const actions = {
  setDroneData({ commit }, payload) {
    commit('updateDroneData', payload)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
