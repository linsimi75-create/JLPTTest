<template>

    <div class="question-nav">
      <div class="question-grid">
        <t-button
          v-for="(question, idx) in questions"
          :key="idx"
          :theme="
            currentQuestionIndex === idx
              ? 'primary'
              : (question.answered
                ? (question.userKey === question.answer ? 'success' : 'danger')
                : 'default')
          "
          :variant="currentQuestionIndex === idx ? 'base' : 'outline'"
          @click="$emit('goToQuestion', idx)"
          size="small"
          class="question-btn"
        >
          {{ idx + 1 }}
        </t-button>
      </div>
      <!-- 移除上一题、下一题、提交按钮，仅保留题号按钮 -->
    </div>

</template>

<script setup lang="ts">
import { defineProps, defineEmits } from 'vue';
const props = defineProps<{
  questions: any[],
  currentQuestionIndex: number,
  isSubmitDisabled: boolean
}>();
const emit = defineEmits(['goToQuestion', 'previousQuestion', 'nextQuestion', 'submit']);
</script>

<style scoped lang="less">
.question-nav {
  padding: 16px;
  background: #fff;
  border: 1px solid #eee;
  border-radius: 4px;
  .question-grid {
    width: 200px;
    display: grid;
    grid-template-columns: repeat(5, 1fr);
    grid-gap: 10px;
    margin-bottom: 16px;
    .question-btn {
      width: 40px;
      height: 40px;
      padding: 0;
    }
  }
  .navigation-buttons {
    margin-top: 20px;
    display: flex;
    gap: 8px;
  }
}
</style>
