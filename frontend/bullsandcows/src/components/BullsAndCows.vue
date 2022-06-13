<template>
  <div>
    <div v-if="nicknameForm" class="form">
      <form class="form-container">
        <h1>Введите никнейм!</h1>
        <input v-model="nickname" type="text" placeholder="Никнейм" required>
        <br/>
        <button class="button" 
        type="button"
        @click="sendNickname"
        >Войти
        </button>
      </form>
    </div>
    <div v-if="gameForm" class="game-form">
      <section class="game-form-container">
        <h1>Быки и коровы</h1>
        <h1 v-if="win">Вы победили! Ответ: {{ answer }}. Доступна статистика по игре!</h1>
        <h1 v-if="lose">Вы проиграли( Доступна статистика по игре!</h1>
        <p v-if="startButton">Компьютер загадывает 4-х значное число. 
          Цифры загаданного числа не повторяются. 
          Попытайтесь угадать загаданное число. 
          Компьютер сообщает, сколько цифр точно угадано (бык) 
          и сколько цифр угадано без учета позиции (корова).
        </p>
        <TimerCounter v-if="gameButton" 
        :unrestricted="unrestricted"
        :turnRestrict="turnRestrict" 
        :timeRestrict="timeRestrict" 
        :turnCount="turnCount" 
        @current="timing" 
        @stop="finishing"/>
        <button v-if="startButton" class="game-button" 
        type="button" 
        @click="startGame"
        >Начать новую игру
        </button>
        <form v-if="gameButton">
          <label>Введите 4-х значное число (цифры не повторяются):</label>
          <br/>
          <input v-model="guess" type="text" placeholder="Ответ" class="input" 
          min="4" max="4" required>
          <br/>
          <button class="game-button" 
          type="button" 
          @click="sendTurn"
          >Проверить
          </button>
        </form>
        <StatsTables 
        :turns="turns" 
        :stats="stats"/>
      </section>
    </div>
  </div>
</template>

<script>
import StatsTables from '../components/StatsTables.vue'
import TimerCounter from '../components/TimerCounter.vue'

export default {
  name: 'BullsAndCows',
  components:{ StatsTables, TimerCounter },
  data() {
    return {
      nicknameForm: true,
      gameForm: false,
      startButton: true,
      gameButton: false,

      api: 'http://localhost:8081',
      nickname: '',
      gameId: 0,
      guess: '',
      answer: '',
      unrestricted: true,
      turnRestrict: 0,
      timeRestrict: 0,
      turnCount: 0,
      currentTime: '00:00:00',
      win: false,
      lose: false,

      turns: [],
      stats: []
    }
  },
  methods: {
    async sendNickname() {
      var regex = new RegExp("^[a-zA-Z0-9]+$");
      if(regex.test(this.nickname)) {
        const res = await fetch(this.api + '/player/' + this.nickname, {
          method: 'GET'
        })

        const player = await res.json()
        if(!player.nickname) {
          await fetch(this.api + '/player/new/' + this.nickname, {
            method: 'POST'
          })
        }

        this.nicknameForm = false
        this.gameForm = true
        this.getStats()
      }
    },

    async startGame() {
      this.win = false
      this.lose = false
      this.answer = ''
      
      const res = await fetch(this.api + '/game/new/' + this.nickname, {
          method: 'POST'
        })

      const game = await res.json()
      this.gameId = game.id
      this.unrestricted = game.unrestricted
      this.turnRestrict = game.turnRestrict
      const time = new Date('January 1, 2000 ' + game.timeRestrict)
      this.timeRestrict = time.getHours() * 3600 + time.getMinutes() * 60 + time.getSeconds()

      this.startButton = false
      this.gameButton = true
    },

    async sendTurn() {
      if(this.guess.length == 4) {
        for (var i = 0; i < this.guess.length; i++) {
          if(isNaN(parseInt(this.guess.charAt(i), 10))){
            return
          }
          else {
            if(this.guess.split(this.guess.charAt(i)).length > 2)
              return
          }
        }

        this.turnCount++
        const res = await fetch(this.api + '/turn/new', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            game: this.gameId,
            guess: this.guess,
            time: this.currentTime
          })
        })

        const turn = await res.json()
        this.turns.push({
          guess: turn.guess,
          bulls: turn.bulls,
          cows: turn.cows
        })

        if(turn.bulls == 4) {
          this.win = true
          this.answer = turn.guess
          this.finishing()
        }
        else {
          if(!this.unrestricted & this.turnCount >= this.turnRestrict){
            this.finishing()
          }
        }
      }
    },

    timing(data) {
      this.currentTime = data
    },

    async finishing() {
      this.startButton = true
      this.gameButton = false

      this.guess = ''
      this.turnCount = 0
      this.currentTime = '00:00:00'
      this.turns = []

      if(!this.win) {
        this.lose = true
      }
  
      await fetch(this.api + '/game/finish/' + this.gameId, {
          method: 'PUT'
      })

      this.getStats()
    },

    async getStats() {
      const res = await fetch(this.api + '/player/' + this.nickname + "/stats", {
          method: 'GET'
      })

      const stat = await res.json()
      this.stats = []
      for(var i = 0; i < stat.length; i++){
        this.stats.push(stat[i])
      }
    }
  }
}
</script>

<style>
/*nickname form*/

.form {
  margin-left: auto;
  margin-right: auto;
  margin-top: auto;
  margin-bottom: auto;
  text-align: center; 
}

.form-container {
  color: #fafafa;
  max-width: 20%;
  padding: 30px;
  margin-left: auto;
  margin-right: auto;
  margin-top: auto;
  margin-bottom: auto;
  background-color: #353535;
  border-radius: 25px;
}

.form-container input[type=text] {
  width: 50%;
  padding: 15px;
  margin-top: 15px;
  margin-left: auto;
  margin-right: auto;
  border: none;
  border-radius: 10px;
  background: #fafafa;
}

.form-container .button {
  width: 150;
  color: #fafafa;
  padding: 16px 16px;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  margin-top: 15px;
  margin-left: auto;
  margin-right: auto;
  opacity: 1;
  background-color: #ff9966;
}

.form-container .button:hover {
  opacity: 0.8;
}

/*guess form*/

.game-form {
  margin-left: 30px;
  margin-right: 30px;
  margin-top: auto;
  margin-bottom: auto;
  text-align: center; 
}

.game-form-container {
  color: #fafafa;
  max-width: 100%;
  padding: 30px;
  margin-left: 30px;
  margin-right: 30px;
  margin-top: auto;
  margin-bottom: auto;
  background-color: #353535;
  border-radius: 25px;
}

.input[type=text] {
  width: 50%;
  padding: 15px;
  margin-top: 15px;
  margin-left: auto;
  margin-right: auto;
  border: none;
  border-radius: 10px;
  background: #fafafa;
}

.game-form-container .game-button {
  width: 150;
  color: #fafafa;
  padding: 16px 16px;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  margin-top: 15px;
  margin-left: auto;
  margin-right: auto;
  opacity: 1;
  background-color: #ff9966;
}

.game-form-container .game-button:hover {
  opacity: 0.8;
}
</style>