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

df_worker = pd.concat([df_upload_raw_news,df_download_update_news], sort=False, axis=0)
df_intermediate_layer_news = df_worker.drop_duplicates()
df_intermediate_layer_news.reset_index(drop=True, inplace=True)
df_intermediate_layer_news.to_csv(r'C:\Sprint\news_intermediate_layer.csv', encoding='utf-8', header='False')