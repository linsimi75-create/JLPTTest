<script setup lang="ts">

import { ref, onMounted } from 'vue';

import type { CardProps } from 'tdesign-vue-next';

const cover: CardProps['cover'] = 'https://tdesign.gtimg.com/site/source/card-demo.png';

// 点击按钮时，滚动容器
const sliderContainer = ref<HTMLElement | null>(null);

onMounted(() => {
    sliderContainer.value = document.querySelector('.sliderContainer');
});

</script>

<template>
    <div class="content-slider">
        <div class="lef_bt">
            <t-icon name="chevron-left" size="36"
                @click="sliderContainer!.scrollBy({ left: -300, behavior: 'smooth' })" />
        </div>
        <div class="sliderContainer">

            <div class="slid">
                <t-card :cover="cover" theme="poster2" :style="{ width: '400px' }" v-for="value in 5"
                    :class="`slider-item` + value" :key="value">
                    <template #footer>
                        <t-comment avatar="https://tdesign.gtimg.com/site/avatar-boy.jpg" author="标题" content="卡片内容" />
                    </template>
                </t-card>
            </div>

        </div>
        <div class="rig_bt">
            <t-icon name="chevron-right" size="36"
                @click="sliderContainer!.scrollBy({ left: 300, behavior: 'smooth' })" />
        </div>
    </div>
</template>

<style scoped lang="less">
// 隐藏滚动条
.content-slider {
    width: 100%;
    overflow: hidden;
    position: relative;
    display: flex;
    align-items: center;
    .lef_bt, .rig_bt {
        position: absolute;
        z-index: 10;
        top: 50%;
        transform: translateY(-50%);
        background-color: rgba(255, 255, 255, 0.8);
        border-radius: 50%;
        cursor: pointer;
        &:hover {
            background-color: rgba(255, 255, 255, 1);
        }
    }
    .lef_bt {
        left: 10px;
    }
    .rig_bt {
        right: 10px;
    }
    .sliderContainer {
        overflow-x: auto;
        scroll-behavior: smooth;
        -ms-overflow-style: none;  // IE and Edge
        scrollbar-width: none;  // Firefox
        &::-webkit-scrollbar {
            display: none;  // Chrome, Safari, Opera
        }
        width: 100%;
        .slid {
            display: flex;
            gap: 16px;
            padding: 16px;
            .t-card {
                flex: 0 0 auto;
            }
        }
    }
    
}
</style>