package es.framework.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import es.framework.R;

public class RadioButtonActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    RadioGroup radioGroup1,radioGroup2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);

        radioGroup1=(RadioGroup)findViewById(R.id.radioGroup1);
        radioGroup2=(RadioGroup)findViewById(R.id.radioGroup2);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent intent=null,chooser=null;
                switch(checkedId)
                {
                    case R.id.rexcellent:
                        intent=new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.eskisehirspor.com"));
                        chooser=Intent.createChooser(intent,"Open website using...");
                        if(intent.resolveActivity(getPackageManager())!=null) {

                            startActivity(chooser);
                        }
                        break;
                    case R.id.rverygood:
                        intent=new Intent(Intent.ACTION_VIEW);
                        String latitude="40.989033",longitude= "28.845294";
                        intent.setData(Uri.parse("http://maps.google.com/maps?q="+latitude+","+longitude+" (Label Point)" ));
                        chooser=Intent.createChooser(intent,"Open Map using...");
                        if(intent.resolveActivity(getPackageManager())!=null) {
                            startActivity(chooser);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "No Apps Available", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.rgood:
                        intent=new Intent(Intent.ACTION_SEND);
                        intent.setData(Uri.parse("mailto:"));
                        intent.setType("message/rfc822");
                        String[] send_to={"ealkilicgil@gmail.com"};
                        intent.putExtra(Intent.EXTRA_EMAIL,send_to);
                        //intent.putExtra(Intent.EXTRA_CC,send_to);
                        //intent.putExtra(Intent.EXTRA_BCC,send_to);
                        intent.putExtra(Intent.EXTRA_STREAM,Uri.parse("android.resource://com.es.framework/drawable/"+R.drawable.blackmoon));
                        chooser=Intent.createChooser(intent, "Open Email App using...");
                        if(intent.resolveActivity(getPackageManager())!=null) {
                            startActivity(chooser);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "No Apps Available", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.raverage:
                        intent=new Intent(Intent.ACTION_SEND);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_TEXT, "Heyy wassup check image!!");
                        intent.putExtra(Intent.EXTRA_STREAM,Uri.parse("android.resource://com.es.framework/drawable/"+R.drawable.blackmoon));
                        chooser=Intent.createChooser(intent, "Open App using...");
                        if(intent.resolveActivity(getPackageManager())!=null) {
                            startActivity(chooser);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "No Apps Available", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.rsendmessage:
                        intent=new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, "Heyy wassup !!");
                        chooser=Intent.createChooser(intent, "Open Email App using...");
                        if(intent.resolveActivity(getPackageManager())!=null) {
                            startActivity(chooser);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "No Apps Available", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.rdial:
                        intent=new Intent(Intent.ACTION_DIAL);

                        intent.setData(Uri.parse("tel:05396812678"));

                        if(intent.resolveActivity(getPackageManager())!=null) {
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "No Apps Available", Toast.LENGTH_SHORT).show();
                        }
                        break;

                }
            }
        });
        radioGroup2.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId)
        {
            case R.id.rnormalnot:
                showNormalViewNotification();

                break;
            case R.id.rbigtextnot:
                Toast.makeText(getApplicationContext(),"Super Cool",Toast.LENGTH_SHORT).show();
                break;

            case R.id.rbigpicturenot:
                Toast.makeText(getApplicationContext(), "Best", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rinboxstylenot:
                Toast.makeText(getApplicationContext(),"Super Cool",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showNormalViewNotification() {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(RadioButtonActivity.this);
        builder.setContentTitle("Normal Regular Notification ");
        builder.setContentText("ESESES KİKİKİ ESKİ ESKİ ES!!!");
        builder.setSmallIcon(R.drawable.checked);
        builder.setTicker("This is the ticker!!");
        builder.setAutoCancel(true);

        //Notificasyona tıklayınca bu evente gider
        Intent intent=new Intent(RadioButtonActivity.this,AnimationActivity.class);
        TaskStackBuilder stackBuilder=TaskStackBuilder.create(this);
        stackBuilder.addParentStack(AnimationActivity.class);//Açacağımız aktivite!!
        stackBuilder.addNextIntent(intent);
        PendingIntent pi_main=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);


        //Buttonları eklediğimiz kısım
        Intent intent_settings=new Intent(RadioButtonActivity.this,PopupActivity.class);
        TaskStackBuilder stackBuilder_settings=TaskStackBuilder.create(this);
        stackBuilder_settings.addParentStack(PopupActivity.class);//Açacağımız aktivite!!
        stackBuilder_settings.addNextIntent(intent);
        PendingIntent pi_settings=stackBuilder_settings.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pi_main);
        builder.addAction(R.drawable.sky,"Settings",pi_settings);

        Notification notification=builder.build();
        NotificationManager manager= (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        manager.notify(1234,notification);
    }
}