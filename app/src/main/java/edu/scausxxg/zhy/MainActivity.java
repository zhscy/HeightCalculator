package edu.scausxxg.zhy;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    Button bt;
    double weight;
    String sex;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取性别
        RadioGroup sexGroup = findViewById(R.id.sex);
        sexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton sexSelect = findViewById(checkedId);
                sex = sexSelect.getText().toString();
            }
        });

        bt = findViewById(R.id.bt);
        // 菜单点击事件
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 获取输入的体重
                EditText editText1 = findViewById(R.id.edit1);
                String s = editText1.getText().toString();
                // 对话框警告
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("警告")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                if (s.equals("") || s == null) {
                    builder.setMessage("体重为空！");
                    builder.create().show();
                } else if (sex == null) {
                    builder.setMessage("性别为空！");
                    builder.create().show();
                } else {
                    weight = Double.parseDouble(s);
                    // 计算标准身高
                    double calculate;
                    if (sex.equals("男")) {
                        calculate = 170-(62-weight)/0.6;
                    } else {
                        calculate = 158-(52-weight)/0.5;
                    }

                    // 保留两位小数
                    BigDecimal b = new BigDecimal(calculate);
                    calculate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                    result = findViewById(R.id.result);
                    result.setText("运算结果为：" + calculate);

                    // 弹出菜单
                    bt.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                        @Override
                        public void onCreateContextMenu(ContextMenu menu, View source, ContextMenu.ContextMenuInfo menuInfo) {

                            menu.add(Menu.NONE, 0, Menu.NONE, "退出");
                            menu.add(Menu.NONE, 1, Menu.NONE, "取消");
                            System.out.println("创建menu");

                        }
                    });
                    bt.showContextMenu();
                }
            }
        });
    }


    /**
     * 处理菜单
     * @param menuItem
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 0) {
            System.out.println("finisth");
            finish();
        }
        return true;

    }

}
