import axios from "axios";
import AxiosInstance from "@/uitls/axios";





// /front/question/ByRepoId
export const QuestionByRepoIdApi = (ifTrue:boolean, id: number) => {
   return AxiosInstance.get(`/question/list?repoId=${id}`, ifTrue);
}





export const QuestionListApi = (ifTrue:boolean) => {
    return AxiosInstance.get("/question/list", ifTrue);
}


export const RepoListApi = (ifTrue:boolean) => {
    return AxiosInstance.get("/repo/list", ifTrue);
}








export interface RepoItem{
    id: number;
    repoName: string;
    repoDesc: string;
    createId: string;
    repo_type: string;
    updateTime: string | null;
    createName?: string;
}