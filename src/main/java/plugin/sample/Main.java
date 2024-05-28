package plugin.sample;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

public final class Main extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        /*　練習
        int number = 10;
        int number2 = 5;
        if(number >= 10 && number2 > 5){
            System.out.println("A");
        } else if (number < 10 && number2 > 5) {
            System.out.println("B");
        }
        else {
            System.out.println("C");
        }

        switch (number){
            case 1 -> System.out.println();


        }
        */

        /* 配列とistの練習
        String[] stringArray = new String[]{"test1","test2","test3"};
        List<String> stringList = List.of("test1","test2","test3");
        stringList.add("test4");    //test4を追加
        stringList.remove(1);   //index 一個目が0、二個目を1
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"test1");
        map.put(2,"test2");
        map.put(3,"test3");
        String value = map.get(1);  //1,"test1"を取得

     */


    }


    /**
     * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
     *
     * @param e イベント
     */

    @EventHandler

    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) throws IOException {
        // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
        Player player = e.getPlayer();
        World world = player.getWorld();

        /*
        countの値をBigInteger型のvalに入れる
        BigInteger val = new BigInteger(String.valueOf(count));
         */

        // 花火イベント

        // 花火オブジェクトをプレイヤーのロケーション地点に対して出現させる。
        Firework firework = world.spawn(player.getLocation(), Firework.class);

        // 花火オブジェクトが持つメタ情報を取得。
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        // メタ情報に対して設定を追加したり、値の上書きを行う。
        // 今回は青色で星型の花火を打ち上げる。
        fireworkMeta.addEffect(
                FireworkEffect.builder()
                        .withColor(Color.YELLOW)
                        .withColor(Color.PURPLE)
                        .withColor(Color.NAVY)
                        .with(Type.BALL_LARGE)
                        .withFlicker()
                        .build());
        fireworkMeta.setPower(8 - 3);



        // 追加した情報で再設定する。
        firework.setFireworkMeta(fireworkMeta);

        Path path = Path.of("firework.text"); //第一階層にこの名前で出力
        Files.writeString(path, "たまやー"); //このメッセージを出力する（追加ではなく上書き）
        player.sendMessage(Files.readString(path));

    }


}