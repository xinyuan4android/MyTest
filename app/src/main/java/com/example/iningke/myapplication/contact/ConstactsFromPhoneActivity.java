package com.example.iningke.myapplication.contact;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iningke.myapplication.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 读取手机通讯录
 *
 * @author hxy
 * @date 2017/2/14
 */
public class ConstactsFromPhoneActivity extends AppCompatActivity implements WordsNavigation.onWordsChangeListener {

    private ListView listView;
    private ContactsAdapter adapter;
    private List<ContactsInfo> dataSource = new ArrayList<>();

    private WordsNavigation wordsNavigation;
    private TextView txt_words;

    private List<String> dataSource_words = new ArrayList<>();//所有首字母的集合
    private Map<String, Integer> dataSource_wordIndex = new LinkedHashMap<>();//所有字母和字母下标的集合；

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constacts_from_phone);
        initView();
    }
//4.4 之前 带拼音的联系人排序 的参数名是sort_key ,4.4 之后，变成下面的了
    //if(android.os.Build.VERSION.SDK_INT>=19){
//    projection[1]="phonebook_label";
//}

    private void initView() {
        wordsNavigation = (WordsNavigation) findViewById(R.id.wordsNavigation);
        wordsNavigation.setOnWordsChangeListener(this);
        txt_words = (TextView) findViewById(R.id.text_words);
        listView = (ListView) findViewById(R.id.listView_contacts);
        adapter = new ContactsAdapter(dataSource);
        listView.setAdapter(adapter);
        try {
            Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Cursor cursor = getContentResolver().query(contactUri,
                    new String[]{"display_name", "sort_key", "contact_id", "data1"},
                    null, null, "sort_key");
            String contactName;
            String contactNumber;
            String contactSortKey;
            int contactId;
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    /*通讯录联系人的名字*/
                    contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    /*通讯录联系人的联系方式*/
                    contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    /*通讯录联系人的ID*/
                    contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                    /*通讯录 根据 拼音加汉字的名字 获取首字母*/
                    contactSortKey = getSortkey(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY)));
                    /*通讯录 联系人的名字 按照 拼音加汉子的方式拼接起来的名字*/
                    //sort_key = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY));
                    ContactsInfo contactsInfo = new ContactsInfo(contactName, contactNumber, contactSortKey, contactId);
                    if (contactName != null)
                        dataSource.add(contactsInfo);
                    if (dataSource_words.size() > 0) {
                        if (!dataSource_words.get(dataSource_words.size() - 1).equals(contactSortKey)) {
                            dataSource_words.add(contactSortKey);
                            dataSource_wordIndex.put(contactSortKey, dataSource.size() - 1);
                        }
                    } else if (dataSource_words.size() == 0) {
                        dataSource_words.add(contactSortKey);
                        dataSource_wordIndex.put(contactSortKey, 0);
                    }
                }
                String[] strings = dataSource_words.toArray(new String[dataSource_words.size()]);
                wordsNavigation.setWords(strings);
                cursor.close();//使用完后一定要将cursor关闭，不然会造成内存泄露等问题
            } else {
                Toast.makeText(ConstactsFromPhoneActivity.this, "cursor = null", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

    private static String getSortkey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        } else
            return "#";   //获取sort key的首个字符，如果是英文字母就直接返回，否则返回#。
    }

    @Override
    public void wordsChange(String words) {
        updateWord(words);
    }

    /**
     * 更新中央的字母提示
     *
     * @param words 首字母
     */
    private void updateWord(String words) {
        /*根据点击的字母来 滚动列表*/
        int position = dataSource_wordIndex.get(words);
        listView.setSelection(position);

        txt_words.setText(words);
        txt_words.setVisibility(View.VISIBLE);
        //清空之前的所有消息
        handler.removeCallbacksAndMessages(null);
        //500ms后让tv隐藏
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                txt_words.setVisibility(View.GONE);
            }
        }, 500);
    }

    private Handler handler = new Handler();

    public class ContactsAdapter extends BaseAdapter {
        private List<ContactsInfo> dataSource;

        public ContactsAdapter(List<ContactsInfo> dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public int getCount() {
            if (dataSource != null) {
                return dataSource.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            ViewHolder holder = null;
            if (convertView == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            holder.itemContactsName.setText(dataSource.get(position).getName());
            holder.itemContactsPhone.setText(dataSource.get(position).getNumber());
            return view;
        }

        class ViewHolder {
            @Bind(R.id.item_contacts_name)
            TextView itemContactsName;
            @Bind(R.id.item_contacts_phone)
            TextView itemContactsPhone;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    //联系人实体类
    public class ContactsInfo {
        private String name;
        private String number;
        private String sortKey;
        private int id;

        public ContactsInfo(String name, String number, String sortKey, int id) {
            this.name = name;
            this.number = number;
            this.sortKey = sortKey;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getSortKey() {
            return sortKey;
        }

        public void setSortKey(String sortKey) {
            this.sortKey = sortKey;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
