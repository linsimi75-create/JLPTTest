<template>
  <t-card class="question-card" :bordered="false" shadow v-if="props">
    <template #header>
      <div class="card-header">
        <span class="num">题号 {{ props.data.id }}</span>
        <div class="creator">
          <span>贡献者：</span>
          <a target="_blank" class="creator">{{ props.data.createName }}</a>
        </div>
      </div>
    </template>

    <!-- 标签 / 题干 / 选项 -->
    <div class="tags">
      <t-tag
        v-for="t in props.data.tags || []"
        :key="t"
        theme="primary"
        variant="light"
        size="small"
      >
        {{ t }}
      </t-tag>
    </div>

    <t-divider dashed style="margin:12px 0" />
    <div class="stem">{{ props.data.stem }}</div>

    <!-- 单选框：judged 后整组禁用 -->
    <t-radio-group
      v-model="userKey"
      class="options"
      :disabled="judged"
      @change="onSelect"
    >
      <t-radio
        v-for="opt in props.data.option_list || []"
        :key="opt.key"
        :value="opt.key"
        shape="square"
      >
        <span class="option-text">
          <strong>{{ opt.key }}.</strong> {{ opt.text }}
        </span>
      </t-radio>
    </t-radio-group>

    <!-- 简单结果提示 -->
    <div v-if="judged" style="margin-top:12px;font-weight:bold">
      <span v-if="isRight" style="color:#52c41a">回答正确</span>
      <span v-else style="color:#ff4d4f">回答错误</span>
    </div>
  </t-card>
</template>

<script setup lang="ts">
import { ref, watch, defineEmits } from 'vue'
import type { QuestionCardProps } from './type'

const props = defineProps<{
  data: QuestionCardProps & { userKey?: string }
}>()
const emit = defineEmits(['update:userKey'])

const userKey = ref(props.data.userKey || '')   // 学生选中的 key
const judged  = ref(false)// 是否已经判分
const isRight = ref(false)// 是否答对

// 外部 userKey 变化时同步内部
watch(() => props.data.userKey, (val) => {
  if (val !== undefined) userKey.value = val
})

// 选中后立即判分并通知父组件
function onSelect() {
  if (judged.value) return
  isRight.value = userKey.value === props.data.answer
  judged.value  = true
  emit('update:userKey', userKey.value)
  // debug 输出
  console.log('QuestionCard 选中:', {
    id: props.data.id,
    userKey: userKey.value,
    answer: props.data.answer,
    isRight: isRight.value
  })
}
</script>

<style scoped>
/* ---------- 卡片 ---------- */
.question-card{max-width:1280px;margin-bottom: 40px;}

/* 深度穿透 */
/* 题号和贡献者分布在两侧 */
/* [@vue/compiler-sfc] ::v-deep usage as a combinator has been deprecated. Use :deep(<inner-selector>) instead of ::v-deep <inner-selector>. */

.question-card ::v-deep(.t-card__header){padding:16px 24px 0 24px;display: flex; align-items: center;justify-content: space-between;}

/* ---------- header ---------- */

.card-header{display:flex;align-items:center;gap:12px;margin-bottom:8px;width: 100%;}


.num{font-size:20px;font-weight:600;color:#303133;margin-right:auto}

.creator{display:flex;align-items:center;gap:4px;font-size:14px;color:#606266}

/* ---------- 标签 ---------- */
.tags{display:flex;flex-wrap:wrap;gap:6px;margin-bottom:4px}

/* ---------- 题干 ---------- */
.stem{font-size:17px;line-height:2;margin:18px 0}

/* ---------- 选项纵向排列 t-radio-group ---------- */
/* 深度穿透 */
.question-card ::v-deep(.t-radio-group){width:100%;display:flex;flex-direction:column;align-items:flex-start;}
/* 选项文字 */

.options{margin-top:8px}
.options .t-radio{margin-bottom:8px;font-size:15px;line-height:1.8}

/* ---------- 解析 ---------- */
.explain-collapse{margin-top:16px}
.explain{line-height:1.8;color:#606266}

/* ---------- 底部 ---------- */
.actions{margin-top:16px;text-align:center}
</style>