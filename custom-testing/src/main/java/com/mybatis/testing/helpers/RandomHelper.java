package com.mybatis.testing.helpers;

import com.mybatis.testing.entity.Account;
import com.mybatis.testing.entity.User;

import java.math.BigDecimal;
import java.util.Random;

public class RandomHelper {
    private static final Random random = new Random();

    private static final RandomHelper helper = new RandomHelper();

    public String randomStr(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯昝管卢莫经房裘缪干解应宗丁宣贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄曲家封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘景詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台丛鄂索咸籍赖卓蔺屠蒙池乔阴佟胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后荆红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于单于太叔申屠公孙仲孙轩辕令狐钟离宇文长孙慕容鲜于闾丘司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓跋夹谷宰父谷梁晋楚闫法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况郈有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public String randomMobile() {
        String str = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


    public static User randomUser() {
        User user = new User();
        user.setName(helper.randomStr(5));
        user.setAge(random.nextInt(100));
        user.setAddr(helper.randomStr(5));
        user.setEmail(helper.randomStr(16));
        user.setMobile(helper.randomMobile());
        return user;
    }

    public static Account randomAccount(Integer userId) {
        Account account = new Account();
        account.setUser_id(userId);
        account.setAmount(BigDecimal.valueOf(random.nextInt(50000)));
        return account;
    }


    public static void main(String[] args) {
        System.out.println(randomUser());
        System.out.println(randomUser());
        System.out.println(randomUser());
        System.out.println(randomUser());


        System.out.println(randomAccount(1));
        System.out.println(randomAccount(2));
        System.out.println(randomAccount(3));
        System.out.println(randomAccount(4));
    }

}
