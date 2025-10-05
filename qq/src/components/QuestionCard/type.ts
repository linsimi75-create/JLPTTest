

  interface QuestionCardProps {
  id:           string;
  stem:          string;
  option_list:       { key: string; text: string }[];
  answer:     string;
  analysis:       string;
  createName:   string;
  tags:          string[];
}

export type { QuestionCardProps };