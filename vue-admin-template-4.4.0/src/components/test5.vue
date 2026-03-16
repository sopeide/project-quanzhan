<template>
  <div ref="sceneContainer" class="simulator-container"></div>
</template>

<script>
import * as THREE from 'three'
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader.js'
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js'
import { mapState } from 'vuex'

export default {
  name: 'DroneSimulator',
  data() {
    return {
      scene: null,
      camera: null,
      controls: null,
      renderer: null,
      droneModel: null,
      quaternion: { x: 0, y: 0, z: 0, w: 1 },
      scaleFactor: 1
    }
  },
  computed: {
    ...mapState('drone', ['pitch', 'yaw', 'roll'])
  },
  mounted() {
    this.initScene()
    this.loadDroneModel()
    this.animate()

    setInterval(this.updateDroneAttitude, 100)
  },
  methods: {
    initScene() {
      this.scene = new THREE.Scene()
      this.camera = new THREE.PerspectiveCamera(
        50,
        this.$refs.sceneContainer.clientWidth / this.$refs.sceneContainer.clientHeight,
        0.01,
        1000
      )
      this.camera.position.set(1, 1, 1)
      this.camera.lookAt(0, 0, 0)

      this.renderer = new THREE.WebGLRenderer({ antialias: true })
      this.renderer.setSize(this.$refs.sceneContainer.clientWidth, this.$refs.sceneContainer.clientHeight)
      this.$refs.sceneContainer.appendChild(this.renderer.domElement)
      this.renderer.setClearColor(0xffffff)

      const light = new THREE.HemisphereLight(0xffffff, 0x444444)
      this.scene.add(light)

      // 添加箭头标注的坐标轴
      const origin = new THREE.Vector3(0, 0, 0)
      const length = 0.5

      const xAxis = new THREE.ArrowHelper(
        new THREE.Vector3(1, 0, 0), // 方向
        origin,
        length,
        0x0000ff // 蓝色 X
      )
      const yAxis = new THREE.ArrowHelper(
        new THREE.Vector3(0, 1, 0),
        origin,
        length,
        0x00ff00 // 绿色 Y
      )
      const zAxis = new THREE.ArrowHelper(
        new THREE.Vector3(0, 0, 1),
        origin,
        length,
        0xffff00 // 黄色 Z
      )

      this.scene.add(xAxis)
      this.scene.add(yAxis)
      this.scene.add(zAxis)

      this.controls = new OrbitControls(this.camera, this.renderer.domElement)
      this.controls.enableDamping = true
      this.controls.dampingFactor = 0.1
      this.controls.enableZoom = true
      this.controls.zoomSpeed = 0.5
      this.controls.rotateSpeed = 0.6
      this.controls.target.set(0, 0, 0)
      this.controls.update()
    },

    loadDroneModel() {
      const loader = new GLTFLoader()
      loader.load(
        '/static/glbmode/Drone.glb',
        gltf => {
          this.droneModel = gltf.scene
          this.droneModel.scale.set(this.scaleFactor, this.scaleFactor, this.scaleFactor)
          this.droneModel.position.set(0, 0, 0)
          this.scene.add(this.droneModel)
        },
        undefined,
        error => {
          console.error('模型加载失败:', error)
        }
      )
    },

    animate() {
      requestAnimationFrame(this.animate)

      if (this.droneModel) {
        const q = new THREE.Quaternion(
          this.quaternion.x,
          this.quaternion.y,
          this.quaternion.z,
          this.quaternion.w
        )
        this.droneModel.setRotationFromQuaternion(q)
        this.droneModel.position.set(0, 0, 0)
      }

      this.controls.update()
      this.renderer.render(this.scene, this.camera)
    },

    updateDroneAttitude() {
      if (!this.droneModel || this.pitch == null || this.yaw == null || this.roll == null) return

      const degToRad = deg => (deg * Math.PI) / 180
      const pitchRad = degToRad(this.pitch)
      const yawRad = degToRad(this.yaw)
      const rollRad = degToRad(this.roll)

      const qPitch = new THREE.Quaternion().setFromAxisAngle(new THREE.Vector3(1, 0, 0), pitchRad)
      const qYaw = new THREE.Quaternion().setFromAxisAngle(new THREE.Vector3(0, 1, 0), yawRad)
      const qRoll = new THREE.Quaternion().setFromAxisAngle(new THREE.Vector3(0, 0, 1), rollRad)

      const qCombined = new THREE.Quaternion()
      qCombined.multiply(qYaw).multiply(qPitch).multiply(qRoll)

      this.quaternion = {
        x: qCombined.x,
        y: qCombined.y,
        z: qCombined.z,
        w: qCombined.w
      }
    }
  }
}
</script>

<style scoped>
.simulator-container {
  width: 100%;
  height: 100vh;
  background-color: #f9f9f9;
}
</style>