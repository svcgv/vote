package com.indihx.system.vo;

public class ScoreRuleInfoVo extends BaseVo{
    private String ruleId;//评分规则ID

    private String parentId;//评分模版ID

    private String scoreDesc;//评分内容

    private String scoreStan;//评分标准

    private Short ruleScore;//分数

    private String tmSmp;//时间

    

    public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getScoreDesc() {
        return scoreDesc;
    }

    public void setScoreDesc(String scoreDesc) {
        this.scoreDesc = scoreDesc == null ? null : scoreDesc.trim();
    }

    public String getScoreStan() {
        return scoreStan;
    }

    public void setScoreStan(String scoreStan) {
        this.scoreStan = scoreStan == null ? null : scoreStan.trim();
    }

    public Short getRuleScore() {
        return ruleScore;
    }

    public void setRuleScore(Short ruleScore) {
        this.ruleScore = ruleScore;
    }

    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }
}