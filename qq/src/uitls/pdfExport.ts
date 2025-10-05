import jsPDF from 'jspdf';

export interface PdfQuestionOption {
  key: string;
  text: string;
}

export interface PdfQuestion {
  stem: string;
  option_list: PdfQuestionOption[];
  answer: string;
  userKey: string;
}

export interface PdfExportStyle {
  titleFontSize?: number;
  questionFontSize?: number;
  optionFontSize?: number;
  fontFamily?: string;
  fontStyleBlod?: string; // 题目/标题字体样式
  fontStyleOption?: string; // 选项字体样式
  colorCorrect?: [number, number, number];
  colorWrong?: [number, number, number];
  colorDefault?: [number, number, number];
}

const defaultStyle: PdfExportStyle = {
  titleFontSize: 16,
  questionFontSize: 13,
  optionFontSize: 11,
  fontFamily: 'fontname',
  fontStyleBlod: 'bold',
  colorCorrect: [0, 180, 0],
  colorWrong: [255, 0, 0],
  colorDefault: [0, 0, 0],
};


// 设置Title
function renderTitle(doc: jsPDF, style: PdfExportStyle, yPosition: number) {
  doc.setFont(style.fontFamily!, style.fontStyleBlod!);
  doc.setFontSize(style.titleFontSize!);
  doc.text('答卷結果', 10, yPosition);
  return yPosition + 10;
}

// 设置问题
function renderQuestion(doc: jsPDF, question: PdfQuestion, questionIndex: number, style: PdfExportStyle, yPosition: number) {
  doc.setTextColor(...style.colorDefault!);
  doc.setFont(style.fontFamily!, style.fontStyleBlod!);
  doc.setFontSize(style.questionFontSize!);
  // 自动换行处理
  const maxWidth = 185; // 页面宽度-边距
  const questionText = `${questionIndex + 1}. ${question.stem}`;
  const lines = doc.splitTextToSize(questionText, maxWidth);
  doc.text(lines, 10, yPosition);
  return yPosition + lines.length * 7 + 2;
}
// 设置选项
function renderOptions(doc: jsPDF, question: PdfQuestion, style: PdfExportStyle, yPosition: number) {
  const maxWidth = 180;
  let hasAnswer = question.userKey && question.userKey !== '__NO_ANSWER__';
  question.option_list.forEach(option => {
    doc.setFont(style.fontFamily!, style.fontStyleBlod!);
    doc.setFontSize(style.optionFontSize!);
    let optionText = `${option.key}. ${option.text}`;
    if (option.key === question.answer) optionText += '（正解）';
    if (hasAnswer && option.key === question.userKey) {
      if (question.userKey === question.answer) {
        doc.setTextColor(...style.colorCorrect!);
      } else {
        doc.setTextColor(...style.colorWrong!);
      }
    } else {
      doc.setTextColor(...style.colorDefault!);
    }
    // 自动换行处理
    const lines = doc.splitTextToSize(optionText, maxWidth);
    doc.text(lines, 14, yPosition);
    yPosition += lines.length * 7;
  });
  // 未作答时额外显示
  if (!hasAnswer) {
    doc.setFont(style.fontFamily!, style.fontStyleBlod!);
    doc.setFontSize(style.optionFontSize!);
    doc.setTextColor(...style.colorWrong!);
    const noAnswerText = '未作答 / No Answer / 未回答';
    const lines = doc.splitTextToSize(noAnswerText, maxWidth);
    doc.text(lines, 14, yPosition);
    yPosition += lines.length * 7;
  }
  return yPosition;
}

export function exportExamPdf(
  questions: PdfQuestion[],
  filename = '答卷.pdf',
  style: PdfExportStyle = defaultStyle
) {
  const doc = new jsPDF();
  let yPosition = 10;
  yPosition = renderTitle(doc, style, yPosition);
  questions.forEach((question, questionIndex) => {
    yPosition = renderQuestion(doc, question, questionIndex, style, yPosition);
    yPosition = renderOptions(doc, question, style, yPosition);
    doc.setTextColor(...style.colorDefault!);
    yPosition += 3;
    if (yPosition > 270) { doc.addPage(); yPosition = 10; }
  });
  doc.save(filename);
}
