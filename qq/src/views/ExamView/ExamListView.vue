import { Notification } from 'tdesign-vue-next';
<script lang="ts" setup>
import QuestionCard from '@/components/QuestionCard/index.vue';
import QuestionNav from '@/components/QuestionNav.vue';
import type { QuestionCardProps } from '@/components/QuestionCard/type';
import { ref, computed, nextTick, onMounted, watch } from 'vue';
import { exportExamPdf } from '@/uitls/pdfExport';
import "@/assets/fontname.js";
import "@/assets/fontname-normal.js";
import { NotifyPlugin,DialogPlugin} from 'tdesign-vue-next';
import {QuestionByRepoIdApi}   from '@/api/index.ts';
import { useRoute, } from 'vue-router';

// 获取路由参数中的 repoId
const route = useRoute();

onMounted(() => {
    const repoId = Number(route.params.repoId);
    if (repoId) {
        getQuestionsForRepo(repoId);
    } else {
        console.error('ExamListView: 缺少 repoId 参数，无法获取题目列表');
        NotifyPlugin.error({
            title: '错误',
            content: '缺少题库ID，无法加载题目列表！',
            duration: 5000,
        });
    }
});


const getQuestionsForRepo = (repoId: number) => {
    QuestionByRepoIdApi(false, repoId).then((response) => {
        const rawList = response.data || [];
        const formattedList = rawList.map((item: any) => ({
            id: item.id,
            accuracyRate: item.accuracyRate ?? 0.85,
            analysis: item.analysis ?? '',
            answer: item.answer ?? '',
            deleted: item.deleted ?? 0,
            createName: item.createName ?? '未知',
            // 兼容后端字段 optionList 或 option_list，统一为 option_list 且结构为 {key,text}[]
            option_list: (item.options ?? item.optionList ?? []).map((opt: any) => ({ key: opt.key ?? opt.optionLabel ?? opt.label, text: opt.text ?? opt.content ?? opt.value })),
            repoId: item.repoId ?? repoId,
            stem: item.stem ?? '',
            tags: item.tags ?? null,
            type: item.type ?? 1,
            // 兼容前端展示
            answered: false,
            userKey: '',
        }));
        questions.value = formattedList;
        console.log('Fetched questions:', questions.value);
    }).catch((error) => {
        console.error('Error fetching questions:', error);
    });
};



const questions = ref<(QuestionCardProps & { answered: boolean; userKey: string })[]>([]);
const currentQuestionIndex = ref(0);
const isSubmitDisabled = computed(() => questions.value.some(q => !q.answered));

function scrollToQuestion(index: number) {
    nextTick(() => {
        const el = document.getElementById('question-card-' + index);
        if (el) {
            el.scrollIntoView({ behavior: 'smooth', block: 'start' });
        }
    });
}


function nextQuestion() {
    if (currentQuestionIndex.value < questions.value.length - 1) {
        currentQuestionIndex.value++;
        scrollToQuestion(currentQuestionIndex.value);
    }
}

function previousQuestion() {
    if (currentQuestionIndex.value > 0) {
        currentQuestionIndex.value--;
        scrollToQuestion(currentQuestionIndex.value);
    }
}

function submit() {
    if (isSubmitDisabled.value) {
        alert("请完成所有题目后再提交！");
    } else {
        alert("已提交答案！");
    }
}
function goToQuestion(index: number) {
    currentQuestionIndex.value = index;
    scrollToQuestion(index);
}

// 自动同步 answered 字段，保证按钮颜色和答题状态一致
// 使用对 questions.userKey 的映射来统一监听，这样即使 questions 被替换也能生效
watch(
    () => questions.value.map(q => q.userKey),
    (newVals, oldVals = []) => {
        newVals.forEach((val, idx) => {
            const oldVal = oldVals[idx];
            if (val === oldVal) return; // 没变化
            const q = questions.value[idx];
            if (!q) return;
            q.answered = !!val;
            // debug 输出
            console.log(`ExamView: 题目${idx + 1} userKey:`, val, 'answered:', q.answered, 'answer:', q.answer);
            console.log('ExamView: 所有题目状态:', questions.value.map((item, i) => ({ i, userKey: item.userKey, answered: item.answered, answer: item.answer })));

            // 答题后自动切换 currentQuestionIndex 到下一个未作答题目
            if (q.answered && currentQuestionIndex.value === idx) {
                const nextIdx = questions.value.findIndex((item, i) => i > idx && !item.answered);
                if (nextIdx !== -1) {
                    currentQuestionIndex.value = nextIdx;
                    scrollToQuestion(nextIdx);
                }
            }
        });

        // 检查是否全部作答，全部作答后弹窗提示下载 PDF
        if (questions.value.length > 0 && questions.value.every(item => item.answered)) {
            setTimeout(() => {
                if (window.confirm('所有题目已作答，是否下载答卷（PDF版）？')) {
                    downloadPdf();
                }
            }, 200);
        }
    },
    { immediate: true }
);

function downloadPdf() {
    const dialog = DialogPlugin({
        header: '导出确认',
        body: '确定要导出当前答卷为PDF吗？未作答的题目将直接判为错误。',
        confirmBtn: '确定',
        cancelBtn: '取消',
        onConfirm: () => {
            dialog.destroy();
            exportExamPdf(
                questions.value.map(q => ({
                    stem: q.stem,
                    option_list: q.option_list,
                    answer: q.answer,
                    userKey: q.userKey || '__NO_ANSWER__',
                }))
            );
            NotifyPlugin.success({
                title: '导出成功',
                content: '答卷PDF已生成并下载！',
                duration: 3000,
            });
        },
        onCancel: () => {
            dialog.destroy();
            NotifyPlugin.info({
                title: '已取消',
                content: '您已取消导出操作。',
            });
        },
    });
}
</script>


<template>
<div class="ExamListView">
    <t-row :gutter="[24, 16]">
        <!-- 题目区：只显示当前题目 -->
        <t-col :span="8">
            <div class="question-list">
                <QuestionCard
                    v-for="(q, idx) in questions"
                    :key="q.id"
                    :data="q"
                    v-model:userKey="q.userKey"
                    :id="'question-card-' + idx"
                />
            </div>
        </t-col>
        <!-- 题号导航区 -->
        <t-col :span="4">
              <t-affix :offset-top="80">
            <QuestionNav
                :questions="questions"
                :currentQuestionIndex="currentQuestionIndex"
                :isSubmitDisabled="isSubmitDisabled"
                @goToQuestion="goToQuestion"
                @previousQuestion="previousQuestion"
                @nextQuestion="nextQuestion"
                @submit="submit"
            />
            <t-button style="margin-top:24px; width:100%;" @click="downloadPdf" theme="primary">提交答案生成PDF</t-button>
            </t-affix>
        </t-col>
    </t-row>
</div>
</template>

<style scoped lang="less">
.ExamListView {
    min-height: calc(100vh - 120px);
    .question-list {
        display: flex;
        flex-direction: column;
        gap: 24px;
    }
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
}
</style>