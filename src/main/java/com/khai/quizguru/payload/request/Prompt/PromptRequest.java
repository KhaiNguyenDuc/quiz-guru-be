package com.khai.quizguru.payload.request.Prompt;

import com.khai.quizguru.enums.Level;
import com.khai.quizguru.enums.QuizType;
import com.khai.quizguru.utils.Prompt;
import lombok.Data;

@Data
public abstract class PromptRequest {

    public Integer type;
    public Integer number;
    public String language;
    public Integer level;
    public Integer duration;

    public abstract String getText();
    public QuizType getQuizType(){
        return switch (this.type){
            case 1 -> QuizType.SINGLE_CHOICE_QUESTION;
            case 2 -> QuizType.MULTIPLE_CHOICE_QUESTION;
            default -> QuizType.MIX_QUESTION;
        };
    }

    public Level getLevel(){
        return switch (this.level) {
            case 2 -> Level.MEDIUM;
            case 3 -> Level.HARD;
            default -> Level.EASY;
        };
    }
    public String generatePrompt(){
        String prompt = "";
        if(this instanceof hasVocabulary){
            prompt = Prompt.VOCABULARY_QUIZ_PROMPT;
        }else{
            prompt = switch (this.type){
                case 2 -> Prompt.MULTIPLE_CHOICE_QUIZ_PROMPT;
                case 3 -> Prompt.MIX_CHOICE_QUIZ_PROMPT;
                default -> Prompt.SINGLE_CHOICE_QUIZ_PROMPT;
            };
        }

        return String.format(prompt,
                this.getNumber(),
                this.getQuizType().getValue(),
                this.getLevel().getValue(),
                this.getLanguage());
    }

}
