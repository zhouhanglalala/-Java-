package Test.chapter1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 数据结构与算法第三版习题1.2
 *
 * 从宽X 高Y的二维矩形随机小写字母表中寻找有效单词，表格式如下，最终宽高由给定参数决定
 * a a a a a
 * b b b b b
 * c c c c c
 * d d d d d
 * 路径为表任意方向的四条边以及两条对角线  共12条
 * 以任意起点 任意终点 取连续的字母生成单词   单词最小长度1  最大长度为各线段长
 * 单词的有效性由是否在词库中决定
 * 运行环境 jdk 1.8
 */
public class WordsGame {
    /**
     * 枚举坐标变化趋势 大/小
     */
    public enum Tendency {
        BIG,
        SMALL
    }

    /**
     * 660个单词词库
     */
    private String[] dictionary = {"cancel", "bad", "bicycle", "bring", "analyse", "awful", "bag", "beef", "alphabet",
            "awake", "branch", "without", "ban", "building", "advance", "bar", "appreciate", "absent", "bay", "because", "calculate", "moving", "you", "beer", "addition", "ashamed", "afterwards", "audience", "ad", "adequately", "agreement", "carefully", "active", "bill", "angry", "accurately", "analysis", "whose", "backwards", "airport", "apparent", "buyer", "1", "as", "2", "at", "left", "amusing", "adult", "brother", "replenish", "attitude", "appropriate", "average", "chapter", "be", "abuse", "achieve", "affect", "ahead", "anybody", "admire", "bottle", "sea", "characteristic", "creepeth", "awkwardly", "sixth", "amuse", "grass", "aspect", "by", "annoyed", "cardboard", "after", "aggressive", "god", "lights", "a", "cd", "set", "be", "address", "charge", "business", "artistic", "right", "blood", "breathe", "the", "actor", "battle", "admiration", "fly", "answer", "blue", "days", "fifth", "borrow", "under", "anniversary", "bed", "acceptable", "anti-", "couple", "beauty", "moved", "blonde", "good", "down", "acid", "bet", "act", "ambition", "bird", "midst", "female", "whales", "businessman", "which", "add", "area", "image", "seasons", "check", "brown", "article", "anxiety", "accompany", "celebration", "aside", "adjust", "authority", "blame", "burst", "carry", "bored", "creature", "deep", "blank", "beginning", "baggage", "divided", "additional", "for", "back", "capable", "behave", "signs", "bite", "aunt", "divide", "multiply", "bullet", "over", "winged", "brilliant", "living", "breathing", "approval", "bound", "blessed", "advert", "breed", "better", "form", "although", "approximately", "there", "beach", "baby", "cancer", "avoid", "bid", "he", "age", "atmosphere", "very", "big", "camping", "career", "called", "advanced", "bin", "ago", "channel", "bit", "approach", "attempt", "years", "balance", "bend", "action", "beside", "block", "brave", "darkness", "hath", "bathroom", "burnt", "belt", "made", "birth", "is", "it", "forth", "bell", "heaven", "announce", "afford", "aid", "begin", "annoying", "become", "brought", "aim", "alive", "in", "against", "boyfriend", "air", "cell", "bandage", "bubble", "have", "angle", "man", "ability", "camera", "belief", "together", "bread", "may", "break", "forward", "bent", "change", "subdue", "gathered", "chairman", "cheap", "fowl", "boat", "off", "cheat", "firmament", "century", "awfully", "able", "evening", "atom", "capital", "bacteria", "almost", "arrow", "cent", "upon", "body", "anger", "associate", "second", "blade", "arrangement", "that", "charity", "academic", "backward", "cable", "alter", "all", "always", "border", "actress", "beneath", "void", "alphabetical", "already", "below", "bearing", "author", "shall", "dry", "fill", "basis", "april", "adventure", "army", "and", "were", "background", "ceremony", "attached", "arms", "bunch", "basic", "badly", "birthday", "behind", "behold", "belong", "brightly", "candy", "fruit", "abundantly", "bush", "careful", "certificate", "cause", "best", "box", "artificially", "around", "boy", "accidentally", "breath", "ball", "and", "advise", "of", "busy", "land", "saying", "brick", "make", "artistically", "on", "brief", "abroad", "burn", "certainly", "chance", "alcoholic", "aircraft", "automatic", "betting", "any", "acknowledge", "attraction", "application", "bury", "earth", "bake", "accommodation", "let", "biscuit", "breast", "annoy", "seas", "accurate", "about", "advertising", "absorb", "angrily", "anywhere", "behalf", "bargain", "wherein", "beautiful", "bank", "character", "lesser", "cake", "above", "annual", "let", "band", "brand", "boil", "alcohol", "based", "a.m.", "night", "amaze", "them", "allied", "ancient", "benefit", "morning", "accept", "bad-tempered", "seas", "adopt", "attorney", "animal", "centimetre", "autumn", "bridge", "male", "annually", "accuse", "access", "seed", "activity", "another", "automatically", "association", "believe", "battery", "two", "acquire", "chase", "accident", "anyway", "into", "amused", "calmly", "moveth", "attack", "actively", "advertisement", "attach", "so", "ankle", "arm", "apart", "broken", "art", "alternatively", "bother", "one", "anxiously", "black", "appeal", "actually", "call", "appear", "boring", "calm", "face", "abandon", "absolute", "fish", "afraid", "armed", "associated", "appoint", "ask", "assist", "to", "fourth", "so", "thing", "cabinet", "chart", "open", "but", "calculation", "bus", "agent", "alarming", "attend", "castle", "approximate", "buy", "available", "had", "bomb", "amazing", "basically", "awkward", "up", "us", "budget", "brush", "waters", "heaven", "given", "actual", "cheaply", "attractive", "beard", "celebrate", "adapt", "alphabetically", "camp", "carelessly", "carpet", "campaign", "aloud", "assume", "allow", "blind", "away", "absence", "attract", "book", "rule", "beast", "assistance", "approving", "bitter", "life", "anything", "button", "apple", "admit", "central", "ally", "arrive", "boot", "every", "bath", "unto", "green", "apply", "again", "was", "argue", "bone", "stars", "bedroom", "bye", "artificial", "attempted", "advertise", "base", "dominion", "achievement", "fruitful", "beyond", "cast", "anxious", "broad", "asleep", "catch", "abandoned", "greater", "cash", "between", "case", "give", "biology", "briefly", "arise", "cattle", "agree", "ambulance", "itself", "light", "phone", "among", "anyone", "apparently", "breakfast", "card", "care", "broadcast", "boss", "artist", "bright", "our", "accidental", "out", "capacity", "across", "assure", "place", "chain", "yielding", "alongside", "agency", "chair", "born", "aged", "centre", "brain", "gathering", "great", "apologize", "bore", "beautifully", "amazed", "altogether", "appearance", "cease", "chat", "behold", "cannot", "behaviour", "broadly", "day", "certain", "first", "board", "argument", "beak", "night", "arrest", "adequate", "before", "own", "appointment", "him", "his", "barrier", "chamber", "arrange", "beat", "from", "affair", "bear", "blow", "day", "alarmed", "bitterly", "created", "kind", "bottom", "tree", "likeness", "capture", "august", "both", "careless", "absolutely", "affection", "arrival", "advice", "according", "their", "captain", "bowl",
            "bike", "earth", "aware", "can", "alone", "candidate", "award", "cap", "along", "billion", "car", "cat", "alarm", "said", "herb", "chairwoman", "ceiling", "amount", "butter", "advantage", "assistant", "saw", "alternative", "also", "spirit", "accent", "afternoon", "third", "round", "build", "creeping", "approve", "anticipate", "meat", "attention", "challenge", "category", "account", "apartment", "carrot"
    };

    /**
     * 入口
     */
    public static void main(String[] args) {
        WordsGame wg = new WordsGame();
        wg.getTable(10, 10);
        wg.search();
    }

    /**
     * 统计循环次数
     */
    private long cycleCount = 0;


    //表格
    private char[][] table;
    //查找结果
    private ArrayList<String> result = new ArrayList<>();
    //表格宽度
    private int x;
    //表格高度
    private int y;
    //对角线长度
    private int diagonalLength;

    /**
     * 生成随机小写字母表
     *
     * @param lengthX 棋盘宽度
     * @param lengthY 棋盘长度
     */
    private void getTable(int lengthX, int lengthY) {
        char[] letters = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'
        };
        //数组外层是Y 内层是X
        char[][] table = new char[lengthY][lengthX];
        Random rand = new Random();
        for (int i = 0; i < lengthY; i++) {
            for (int j = 0; j < lengthX; j++) {
                //[0-n) 左闭右开
                int k = rand.nextInt(letters.length);
                table[i][j] = letters[k];
                System.out.print(letters[k] + " ");
            }
            System.out.println("");
        }

        x = table[0].length;
        y = table.length;
        this.diagonalLength = y>=x?x:y;
        this.table = table;
    }

    /**
     * 按12条路径寻找
     */
    private void search() {

        //(0,0)->(0,n-1)左竖线向下
        borderSearch(Tendency.BIG, 0, "y");
        // (0,0)->(n-1,0)上横线向右
        borderSearch(Tendency.BIG, 0, "x");
        //(n-1,0)->(n-1,n-1)右竖线向下
        borderSearch(Tendency.BIG, x - 1, "y");
        //(0,n-1)->(0,0)左竖线向上
        borderSearch(Tendency.SMALL, 0, "y");
        //(0,n-1)->(n-1,n-1)下横线向右
        borderSearch(Tendency.BIG, y - 1, "x");
        //(n-1,0)->(0,0)上横线向左
        borderSearch(Tendency.SMALL, 0, "x");
        //(n-1,n-1)->(n-1,0)右竖线向上
        borderSearch(Tendency.SMALL, x - 1, "y");
        //(n-1,n-1)->(0,n-1)下横线向左
        borderSearch(Tendency.SMALL, y - 1, "x");


        //右上到左下
        diagonalSearch(Tendency.SMALL, Tendency.BIG);
        //左上到右下
        diagonalSearch(Tendency.BIG, Tendency.BIG);
        //右下到左上
        diagonalSearch(Tendency.SMALL, Tendency.SMALL);
        //左下到右上
        diagonalSearch(Tendency.BIG, Tendency.SMALL);

        System.out.println("总共循环次数：" + cycleCount);
        System.out.println("有效单词：" + result);
    }

    /**
     * 按对角线查找 把两条对角线的四种查找方式抽成四个if语句   2*2=4 两个维度
     *
     * @param xTendency x变化趋势
     * @param yTendency y变化趋势
     */
    private void diagonalSearch(Tendency xTendency, Tendency yTendency) {
        //i是单词起始点 j是单词终点
        for (int i = 0; i < diagonalLength; i++) {
            //每次更换起始坐标都初始化容器
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < diagonalLength; j++) {
                ++cycleCount;
                if (xTendency == Tendency.SMALL && yTendency == Tendency.BIG)
                    sb.append(table[j][diagonalLength - j - 1]);
                if (xTendency == Tendency.SMALL && yTendency == Tendency.SMALL)
                    sb.append(table[diagonalLength - j - 1][diagonalLength - j - 1]);
                if (xTendency == Tendency.BIG && yTendency == Tendency.SMALL)
                    sb.append(table[diagonalLength - j - 1][j]);
                if (xTendency == Tendency.BIG && yTendency == Tendency.BIG)
                    sb.append(table[j][j]);
                System.out.println(sb.toString());
                //contains-->O(n)
                if (Arrays.asList(dictionary).contains(sb.toString()) && !result.contains(sb.toString())) {
                    result.add(sb.toString());
                }
            }
        }
    }


    /**
     * 按8条边寻找
     * 固定坐标 固定值  变化边界  变化值
     * x       0       y       j     //(0,0)->(0,n-1)左竖线向下     sb.append(table[j][0]);
     * y       0       x       j     // (0,0)->(n-1,0)上横线向右    sb.append(table[0][j]);
     * x       x-1     y       j     //(n-1,0)->(n-1,n-1)右竖线向下 sb.append(table[j][x-1]);
     * x       0       y       y-j-1 //(0,n-1)->(0,0)左竖线向上     sb.append(table[y - 1 - j][0]);
     * y       y-1     x       j     //(0,n-1)->(n-1,n-1)下横线向右 sb.append(table[y-1][j]);
     * y       0       x       x-j-1 //(n-1,0)->(0,0)上横线向左     sb.append(table[0][x - 1 - j]);
     * x       x-1     y       y-j-1 //(n-1,n-1)->(n-1,0)右竖线向上 sb.append(table[y - 1 - j][x - 1]);
     * y       y-1     x       x-j-1 //(n-1,n-1)->(0,n-1)下横线向左 sb.append(table[y - 1][x - 1 - j]);
     *
     * 知道变化坐标即可知道固定坐标  2*2*2=8  三个维度
     *
     * @param fixedValue     固定值
     * @param changingBorder 变化边界
     * @param tendency       变化边 的变化趋势
     */
    private void borderSearch(Tendency tendency, int fixedValue, String changingBorder) {
        //i是单词起始点 j是单词终点
        for (int i = 0; i < ("y".equals(changingBorder) ? y : x); i++) {
            //每次更换起始坐标都初始化容器
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < ("y".equals(changingBorder) ? y : x); j++) {
                ++cycleCount;
                if ("y".equals(changingBorder) && tendency == Tendency.BIG) {
                    sb.append(table[j][fixedValue]);
                }
                if ("x".equals(changingBorder) && tendency == Tendency.BIG) {
                    sb.append(table[fixedValue][j]);
                }
                if ("y".equals(changingBorder) && tendency == Tendency.SMALL) {
                    sb.append(table[y - 1 - j][fixedValue]);
                }
                if ("x".equals(changingBorder) && tendency == Tendency.SMALL) {
                    sb.append(table[fixedValue][x - 1 - j]);
                }
                System.out.println(sb.toString());
                //contains O(n)
                if (Arrays.asList(dictionary).contains(sb.toString()) && !result.contains(sb.toString())) {
                    result.add(sb.toString());
                }
            }
        }
    }
}