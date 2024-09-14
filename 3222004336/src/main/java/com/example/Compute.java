package com.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class Compute {
    //分词 分词后返回list
    public List<String> participleNlp(String context){
        return participleNlpToTerm(context).stream().map(Term::getName).collect(Collectors.toList());
    }

    //分词 分词后返回term
        public List<Term> participleNlpToTerm(String context){
        return ToAnalysis.parse(context).getTerms();
    }

    //计算余弦相似度
     public double calculateCosSimilarity(Map<String, Integer> vec1, Map<String, Integer> vec2) {
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;
        for (Map.Entry<String, Integer> entry : vec1.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            dotProduct += count * vec2.getOrDefault(word, 0);
            norm1 += Math.pow(count, 2);
        }
        for (Map.Entry<String, Integer> entry : vec2.entrySet()){
            int count = entry.getValue();
            norm2 += Math.pow(count, 2);
        }
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    //计算余弦相似度
    public double calculateCosSimilarity(String context1, String context2){
        return calculateCosSimilarity(participleNlp(context1).stream().collect(Collectors.groupingBy(o -> o, Collectors.summingInt(o -> 1))),
                participleNlp(context2).stream().collect(Collectors.groupingBy(o -> o, Collectors.summingInt(o -> 1))));
    }

    //对比 输出重复率
    public boolean ifPlagiarism(String context1, String context2, double threshold){
        return calculateCosSimilarity(context1, context2) > threshold;
    }

    //对比 阈值为0.7 重复率小于0.7输出false即不抄袭  大于0.7则输出true判定为抄袭
    public boolean ifPlagiarism(String context1, String context2){
        return ifPlagiarism(context1, context2, 0.7);
    }
    

}
