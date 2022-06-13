<template>
    <div>
        <div class="table" v-if="!turns.length==0">
          <h2>Ходы:</h2>
          <table class="table">
            <tr>
              <th v-for="(col, index) in turnColumns" v-bind:key="index">
                {{ col }}
              </th>
            </tr>
            <tr v-for="(t, index) in turns" v-bind:key="index">
              <td v-for="(val,index) in t" v-bind:key="index">
                {{ val }}
              </td>
            </tr>
          </table>
        </div>
        <div class="table" v-if="!stats.length==0">
          <h2>Статистика сыгранных игр:</h2>
          <table class="table">
            <tr>
              <th v-for="(col, index) in statsColumns" v-bind:key="index">
                {{ col }}
              </th>
            </tr>
            <tr v-for="(s, index) in stats" v-bind:key="index">
              <td v-for="(val,index) in s" v-bind:key="index">
                {{ val }}
              </td>
            </tr>
          </table>
        </div>
    </div>
</template>

<script>
export default {
  name: 'StatsTables',
  props:['turns', 'stats'],
  data() {
    return{
        turnColumns: [
        'Догадка',
        'Быки',
        'Коровы'
      ],

      statsColumns: [
        '#',
        'Количество ходов',
        'Время'
      ],
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