# Generated by Django 3.1.7 on 2021-03-13 06:12

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='HeartReadings',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('age', models.FloatField()),
                ('sex', models.FloatField()),
                ('cp', models.FloatField()),
                ('trestbps', models.FloatField()),
                ('chol', models.FloatField()),
                ('fbs', models.FloatField()),
                ('restecg', models.FloatField()),
                ('thalach', models.FloatField()),
                ('exang', models.FloatField()),
                ('oldpeak', models.FloatField()),
                ('slope', models.FloatField()),
                ('ca', models.FloatField()),
                ('thal', models.FloatField()),
            ],
        ),
    ]
