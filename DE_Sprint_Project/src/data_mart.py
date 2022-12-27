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

#Количество новостей данной категории для каждого из источников за все время
#lenta.ru
df_category__all_news_lenta = len(df_intermediate_layer_news[(df_intermediate_layer_news['category']=='Мир') & (df_intermediate_layer_news['link'].str.contains('lenta.ru', regex= True, na=False))])
print(df_category__all_news_lenta)
#www.vedomosti.ru
df_category__all_news_vedomosti = len(df_intermediate_layer_news[(df_intermediate_layer_news['category']=='Мир') & (df_intermediate_layer_news['link'].str.contains('vedomosti.ru', regex= True, na=False))])
print(df_category__all_news_vedomosti)
#tass.ru
df_category__all_news_tass = len(df_intermediate_layer_news[(df_intermediate_layer_news['category']=='Мир') & (df_intermediate_layer_news['link'].str.contains('tass.ru', regex= True, na=False))])
print(df_category__all_news_tass)