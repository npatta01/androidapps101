import csv
from peewee import *
from peewee import create_model_tables, drop_model_tables
import os
import json


try:
    os.remove('ted_talks.db')
except OSError:
    pass
database = SqliteDatabase('ted_talks.db')


class BaseModel(Model):
    class Meta:
        database = database


class Speaker(BaseModel):
    name = CharField(primary_key=True)


class Event(BaseModel):
    name = CharField(primary_key=True)


class Talk(BaseModel):
    url = CharField()
    id = PrimaryKeyField()
    speaker = ForeignKeyField(Speaker)
    name = CharField()
    description = CharField()
    event = ForeignKeyField(Event)
    duration = CharField()
    publish_date = CharField()

#URL,ID,URL,Speaker,Name,Short Summary,Event,Duration,Publish date



database.connect()

#    drop_model_tables([Speaker, Category, TagTalk, Tag, Talk])
create_model_tables([Speaker, Talk, Event])

seen_speakers = {}
seen_events = {}
seen_ids = set()

with database.transaction():
    with open('talks.json', 'r') as f:
        data = json.load(f)
        for elem in data:
            speaker = elem['Speaker']
            event = elem['Event']
            speaker_obj = None
            event_obj = None
            if speaker in seen_speakers:
                speaker_obj = seen_speakers[speaker]
            else:
                speaker_obj = Speaker(name=speaker)
                speaker_obj.save(force_insert=True)
                seen_speakers[speaker] = speaker_obj

            if event in seen_events:
                event_obj = seen_events[event]
            else:
                event_obj = Event(name=event)
                event_obj.save(force_insert=True)
                seen_events[event] = event_obj
            t = Talk()
            t.url = elem['URL']
            t.id = elem['ID']
            t.speaker = speaker_obj
            t.name = elem['Name']
            t.description = elem['Short Summary']
            t.event = event_obj
            t.duration = elem['Duration']
            t.publish_date = elem['Publish date']

            print t.id
            if t.id not in seen_ids:
                seen_ids.add(t.id)
                print ("saving " + t.url)
                t.save(force_insert=True)

database.commit()
database.close()