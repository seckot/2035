import feedparser
import pandas as pd


rss_feeds = ['https://lenta.ru/rss/',
        'https://www.vedomosti.ru/rss/news',
        'https://tass.ru/rss/v2.xml',
    ]

posts = []
feed = []
for url in rss_feeds:
    feed = feedparser.parse(url)
    for post in feed.entries:
        posts.append((post.title, post.link, post.published, post.category))
df_download_update_news = pd.DataFrame(posts, columns=["title", "link", "published", "category"])

df_upload_raw_news = pd.read_csv(r'C:\Sprint\raw_news_data.csv', on_bad_lines='skip')
df_intermediate_layer_news = pd.concat([df_upload_raw_news,df_download_update_news], sort=False, axis=0)

df_intermediate_layer_news.to_csv(r'C:\Sprint\news_intermediate_layer.csv', encoding='utf-8', index=False)
df_intermediate_layer_news_old = pd.read_csv(r'C:\Sprint\news_intermediate_layer.csv', on_bad_lines='skip')

df_intermediate_layer_news = pd.concat([df_intermediate_layer_news_old,df_download_update_news], sort=False, axis=0)

df_intermediate_layer_news["category"] = df_intermediate_layer_news["category"].replace(['Международная панорама'], 'Мир')
df_intermediate_layer_news["category"] = df_intermediate_layer_news["category"].replace(['Экономика и бизнес'], 'Экономика')
df_intermediate_layer_news["category"] = df_intermediate_layer_news["category"].replace(['Моя страна '], 'Россия')
df_intermediate_layer_news["category"] = df_intermediate_layer_news["category"].replace(['Армия и ОПК '], 'Силовые структуры')
df_intermediate_layer_news["category"] = df_intermediate_layer_news["category"].replace(['Наука и техника'], 'Технологии')
df_intermediate_layer_news["category"] = df_intermediate_layer_news["category"].replace(['Авто'], 'Транспорт')
df_intermediate_layer_news["category"] = df_intermediate_layer_news["category"].replace(['Моя страна'], 'Россия')
df_intermediate_layer_news['published'] = pd.to_datetime(df_intermediate_layer_news['published']).dt.date
df_intermediate_layer_news = df_intermediate_layer_news.drop_duplicates()
df_intermediate_layer_news_date = df_intermediate_layer_news.sort_values(by='published')
df_intermediate_layer_news_date.to_csv(r'C:\Sprint\news_intermediate_layer.csv', encoding='utf-8', index=False)