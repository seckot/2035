import pandas as pd

#загрузка промежуточного слоя данных
df_intermediate_layer_news = pd.read_csv(r'C:\Sprint\news_intermediate_layer.csv', on_bad_lines='skip')

#обработка данных

#Название категории
df_category_name = df_intermediate_layer_news['category'].unique()
print(df_category_name)

#Общее количество новостей из всех источников по данной категории за все время
df_category_name_all_news = len(df_intermediate_layer_news[df_intermediate_layer_news['category']=='Мир'])
print(df_category_name_all_news)
