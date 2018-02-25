package com.example.karleinstein.tratan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class BlankFragment extends Fragment {
    public BlankFragment() {
        // Required empty public constructor
    }
    TextView tvName;
    ImageView imgCharactor;
    Button btnStart;
    FileInputStream fileInputStream;
    SharedPreferences sharedPreferences,tellmewhy;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    StringBuffer stringBuffer;
    int karl;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank,container,false);
        tvName=view.findViewById(R.id.tvName);
        imgCharactor=view.findViewById(R.id.imgCharactor);
        btnStart=view.findViewById(R.id.btnStart);
        sharedPreferences=this.getActivity().getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        tellmewhy=this.getActivity().getSharedPreferences("high score",Context.MODE_PRIVATE);
        iloveja();
        switch (getArguments().getInt("key_color"))
        {
            case 1:
                tvName.setText("DO VAN CUONG");
                imgCharactor.setImageResource(R.drawable.ic_launcher_background);
                imgCharactor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"An cai loz bo giet con me may",Toast.LENGTH_SHORT).show();
                    }
                });
                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"Xin loi ban chua du tuoi de choi nhan van nay",Toast.LENGTH_SHORT).show();
                    }
                });
                if (karl>=100)
                {
                    imgCharactor.setImageResource(R.drawable.trash);
                    btnStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(),LOL.class);
                            int ja=1;
                            intent.putExtra("ja",ja);
                            startActivity(intent);
                        }
                    });
                }
                break;
            case 2:
                tvName.setText("VU MINH QUAN");
                imgCharactor.setImageResource(R.drawable.ic_launcher_background);
                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"Xin loi ban chua du tuoi de choi nhan van nay",Toast.LENGTH_SHORT).show();
                    }
                });
                imgCharactor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"An cai loz bo giet con me may",Toast.LENGTH_SHORT).show();
                    }
                });
                if (karl>=200)
                {
                    imgCharactor.setImageResource(R.drawable.loz);
                    btnStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(),LOL.class);
                            int ja1=2;
                            intent.putExtra("ja1",ja1);
                            startActivity(intent);
                        }
                    });
                }


                break;
            case 3:
                    tvName.setText("MAI XUAN BACH");
                    imgCharactor.setImageResource(R.drawable.ic_launcher_background);
                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"Xin loi ban chua du tuoi de choi nhan van nay",Toast.LENGTH_SHORT).show();
                    }
                });
                imgCharactor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"An cai loz bo giet con me may",Toast.LENGTH_SHORT).show();
                    }
                });
                    if (karl>=300)
                    {
                        imgCharactor.setImageResource(R.drawable.vkl);
                        btnStart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(getActivity(),LOL.class);
                                int ja2=3;
                                intent.putExtra("ja2",ja2);
                                startActivity(intent);
                            }
                        });
                    }

                break;
            default:
                tvName.setText("TUAT");
                imgCharactor.setImageResource(R.drawable.tratan);
                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),LOL.class);
                        startActivity(intent);
                    }
                });
                break;

        }
        // Inflate the layout for this fragment
        return view;
    }

    private void iloveja() {
        String dulieu;
        try {
            fileInputStream=getActivity().openFileInput("high_score.txt");
            inputStreamReader=new InputStreamReader(fileInputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            stringBuffer=new StringBuffer();
            while ((dulieu=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(dulieu+"");
            }
            karl=Integer.valueOf(String.valueOf(stringBuffer));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
