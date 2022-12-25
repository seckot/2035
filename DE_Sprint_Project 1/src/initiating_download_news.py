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
df_initiating_download_news = pd.DataFrame(posts, columns=["title", "link", "published", "category"])
df_initiating_download_news.to_csv(r'C:\Sprint\raw_news_data.csv', encoding='utf-8', index=False)
print(df_initiating_download_news)
