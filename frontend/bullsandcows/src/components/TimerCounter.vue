<template>
    <div>
        <h1>{{ formatedTime }}</h1>
        <div v-if="!unrestricted"> 
          <h3>Лимит: {{ restrict }}</h3>
          <table class="table">
            <tr>
              <th>Всего ходов</th>
              <th>Пройдено ходов</th>
              <th>Осталось ходов</th>
            </tr>
            <tr>
              <td>{{ turnRestrict }}</td>
              <td>{{ turnCount }}</td>
              <td>{{ turnRestrict - turnCount }}</td>
            </tr>
          </table>
        </div>
    </div>
</template>

<script>
export default {
  name: 'TimerCounter',
  props:['unrestricted', 'turnRestrict', 'timeRestrict', 'turnCount'],
  data() {
    return{
      timer: null,
      currentTime: 0,
      formatedTime: '00:00:00',
      restrict: this.formatTime(this.timeRestrict)
    }
  },

  mounted() {
    this.startTimer()
  },
  unmounted() {
    this.stopTimer()
  },

  methods: {
    startTimer() {
      this.timer = setInterval(() => {
        this.currentTime++
        this.$emit('current', this.formatTime(this.currentTime))
        this.formatedTime = this.formatTime(this.currentTime)
      }, 1000)
    },

    stopTimer() {
      clearTimeout(this.timer)
    },

    format(time) {
      return time < 10 ? '0' + time : time
    },

    formatTime(time) {
      const hours = Math.floor(time/3600)
      const minutes = Math.floor(time/60) - hours*60
      const secs = time - minutes*60 - hours*3600
      return this.format(hours) + ':' + this.format(minutes) + ':' + this.format(secs)
    }
  },

  watch: {
    currentTime(time) {
      if (!this.unrestricted & time == this.timeRestrict) {
        this.stopTimer()
        this.$emit('stop')
      }
    }
  } 
}
</script>

<style>
.table {
  margin-top: 15px;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 15px;
}
</style>