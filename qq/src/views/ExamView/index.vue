<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import ContributorInfoTag from '@/components/ContributorInfoTag.vue';
import {RepoListApi} from '@/api/index.ts';
import type { RepoItem } from '@/api/index.ts';
import { useRouter, useRoute } from 'vue-router'
const sections = ref();

const router = useRouter();


const repoTypeMap = [
    { title: '官方题库', type: '0' },
    { title: '热门题库', type: '1' },
    { title: '专项题库', type: '2' },
    { title: '自制题库', type: '3' },
];

onMounted(async () => {
    try {
        const response = await RepoListApi(false);
        // response.data 是数组
        const rawList = response.data || [];
        // 按 repoType 分组
        const grouped = repoTypeMap.map(group => {
            const items = rawList.filter((item: { repoType: string; }) => item.repoType === group.type).map((item: { id: any; repoName: any; repoDesc: any; createId: any; createName: any; }) => ({
                id: item.id,
                title: item.repoName,
                desc: item.repoDesc,
                cover: 'https://tdesign.gtimg.com/site/source/card-demo.png', // 模拟封面
                createId: item.createId,
                createName: item.createName,
            }));
            return {
                title: group.title,
                items,
            };
        });
        sections.value = grouped;
        console.log('Fetched sections:', sections.value);
    } catch (error) {
        console.error('Error fetching sections:', error);
    }
});




const getQuestionsForRepo = (repoId: number) => {
    router.push({ name: 'examlist', params: { repoId } });
};



</script>

<template>
    <div class="exam-view">
        <h1>题库中心</h1>
        <div class="section" v-for="section in sections" :key="section.title">
            <h2 class="section-title">{{ section.title }}</h2>
            <t-row :gutter="[24, 16]" class="card-list">
                <template v-for="(item, idx) in section.items.slice(0, 8)" :key="item.id">
                    <t-col :span="3" class="exam-card">
                        <t-card :hover="true" class="exam-card">
                            <img :src="item.cover" alt="cover" class="card-cover" />

                            <div class="card-title">{{ item.title }}</div>
                            <div class="card-desc">{{ item.desc }}</div>
                            <div class="card-action">
                                <t-button @click="getQuestionsForRepo(item.id)">开始练习</t-button>
                            
                                <ContributorInfoTag>
                                    <span class="contributor-tag">贡献者</span>
                                    <span class="contributor-msg">{{ item.createName }}</span>
                                </ContributorInfoTag>
                            </div>
                        </t-card>
                    </t-col>
                </template>
            </t-row>
        </div>
    </div>


</template>

<style scoped lang="less">
.exam-view {
    padding: 24px;
    background-color: #ffffff;
    min-height: 100vh;

    h1 {
        font-size: 24px;
        margin-bottom: 16px;
    }

    .section {
        margin-bottom: 32px;

        .section-title {
            font-size: 20px;
            margin-bottom: 16px;
            border-left: 4px solid #1890ff;
            padding-left: 8px;
        }

        .card-list {
            .exam-card {
                .exam-card {
                    border: 1px solid #f0f0f0;
                    border-radius: 8px;
                    overflow: hidden;
                    transition: box-shadow 0.3s;

                    &:hover {
                        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
                    }

                    .card-cover {
                        width: 100%;
                        height: 120px;
                        object-fit: cover;
                    }

                    .card-title {
                        font-size: 16px;
                        font-weight: bold;
                        margin: 12px;
                    }

                    .card-desc {
                        font-size: 14px;
                        color: #666666;
                        margin: 0 12px 12px 12px;
                        min-height: 40px;
                    }

                    .card-action {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        padding: 12px 0;

                        t-button {
                            background-color: #1890ff;
                            color: #ffffff;
                            border: none;

                            &:hover {
                                background-color: #40a9ff;
                            }
                        }

                        .contributor-tag {
                            display: inline-block;
                            border-radius: 4px;
                            padding: 2px 6px;
                           
                            background: rgba(30,30,30,0.7);
                            color: #8ecfff;
                            font-size: 12px;
                        }
                    }
                    
                }
            }
        }
    }

    .t-sticky-tool {
        z-index: 1000;

    }


}
</style>