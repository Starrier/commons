package org.starrier.common.constant;

/**
 * @author Starrier
 * @date 2019/4/19.
 * <p>
 * Description :
 */
public class Constant {

    /**
     * 敏感词匹配规则
     * <p>
     * 最小匹配规则，如：敏感词库["中国","中国人"]，语句："我是中国人"，匹配结果：我是[中国]人
     * 最大匹配规则，如：敏感词库["中国","中国人"]，语句："我是中国人"，匹配结果：我是[中国人]
     */
    public static final int MIN_MATCH_T_YPE = 1;

    public static final int MAX_MATCH_TYPE = 2;

    public static final String IS_END = "isEnd";

    public static final String ONE = "1";

    public static final String TWO = "2";

    public static final String ZERO = "0";

    public static final String UTF8 = "UTF-8";

    public static final Integer FOUR = 4;

}
