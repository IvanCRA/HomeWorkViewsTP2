# Домашнее задание по Kotlin lev1 №1

## Содержание

- [Задание](#задание)
- [Технологии](#технологии)
- [API](#API)
- [Установка](#установка)


## Задание:

Требуется разработать небольшое приложение, которое отображает плашки с изображениями, полученными от API.
Приложение должно уметь обрабатывать состояние загрузки данных. В примитивном варианте - выводить крутилку по центру экрана. Если есть догрузка данных - то отображать плашку с крутилкой.
Приложение должно уметь обрабатывать ошибки загрузки - отображать заглушку, которая позволит повторить запрос. Если есть пагинация - то плашка должна отображать элемент, тап по которому позволить повторить догрузку данных.


**Ограничения и требования:**
1. Требуется использовать Fragment/Compose
2. Приложение не должно содержать хардкод.
3. Приложение должно использовать ресурсы(resources) для работы
4. В коде можно оставлять комментарии, но в конечной версии нельзя оставлять Log
5. Обработка переворота экрана (загруженные данные не должны грузиться с нуля)

**Что будет плюсом:**
1. Пропорции плашек, соответствуют пропорциям получаемым изображениям (не все API вам могут позволить это сделать)
2. Загружаемые изображения - анимированные (gif)
3. Загружаемые изображения имеют разные пропорции (как в Pinterest)
4. Пагинация (Догрузка данных во время скролла)
5. Использование кэша (как для данных так и для картинок)


**Основные сценарии проверки:**
1. Ошибка загрузки данных. Перед запуском приложения - включить режим полета. После выключения режима полета - тапнуть по заглушке, чтобы данные загрузились
2. Ошибка догрузки данных(если есть пагинация). После запуска приложения, дождаться загрузки данных, включить режим полета и инициировать догрузку данных. После выключения режима полета - тапнуть на повторную догрузку данных,  данные должны догрузиться.

## Технологии

- Kotlin
- View
- ImageView
- ConstraintLayout
- AndroidX
- ViewModel
- FloatState
- RecyclerView
- DiffUtils
- Glide
- Retrofit2

## API
Используется API от [civitai.com](https://civitai.com/), документацию REST API от Civitai можно посмотреть [здесь](https://github.com/civitai/civitai/wiki/REST-API-Reference#get-apiv1images).
Пример JSON ``https://civitai.com/api/v1/images?limit=1``
```JSON
{
  "items": [
    {
      "id": 9173928,
      "url": "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/cc242d6c-f960-4274-aa1d-f22a71e705ef/width=832/cc242d6c-f960-4274-aa1d-f22a71e705ef.jpeg",
      "hash": "UA8N5},:Ioni~C#laKxaoznNwvx]XmRkVstR",
      "width": 832,
      "height": 1216,
      "nsfwLevel": "None",
      "nsfw": false,
      "browsingLevel": 1,
      "createdAt": "2024-04-04T21:24:07.499Z",
      "postId": 1981754,
      "stats": {
        "cryCount": 528,
        "laughCount": 1051,
        "likeCount": 8122,
        "dislikeCount": 0,
        "heartCount": 3313,
        "commentCount": 29
      },
      "meta": {
        "Size": "832x1216",
        "seed": 1938345220,
        "steps": 45,
        "prompt": "AtomicHeartTwinsCosplay in absolute darkness, profoundly no light, holding black-pink heart shaped orbstaff, \npointed hat, translucent skin, Describe the captivating scene captured in the vintage photograph featuring a Bedouin artist skillfully swallowing a massive sword in the style of Final Fantasy, amidst a mesmerized audience. Provide details about the artist's attire, the sword's intricate design, and the expressions of the onlookers as they witness this extraordinary performance., amazing quality, masterpiece, best quality, hyper detailed, ultra detailed, UHD, perfect anatomy, portrait, dof, hyper-realism, majestic, awesome, inspiring, closeup, an weathered outworn old Fantasy cape, smooth, Closeup, by Dring, rust paint peelz, atmospheric haze, cinamatic composition, soft shadows, national geographic style",
        "sampler": "DPM++ 2M",
        "cfgScale": 5,
        "clipSkip": 2,
        "resources": [],
        "negativePrompt": "easynegative, bad proportions, low resolution, bad, ugly, terrible, painting, 3d, render, comic, anime, manga, unrealistic, flat, watermark, signature, worst quality, low quality, normal quality, lowres, simple background, inaccurate limb, extra fingers, fewer fingers, missing fingers, extra arms, (extra legs:1.3), inaccurate eyes, bad composition, bad anatomy, error, extra digit, fewer digits, cropped, low res, worst quality, low quality, normal quality, jpeg artifacts, extra digit, fewer digits, trademark, watermark, artist's name, username, signature, text, words, human,",
        "civitaiResources": [
          {
            "type": "checkpoint",
            "modelVersionId": 345685
          },
          {
            "type": "embed",
            "weight": 1,
            "modelVersionId": 9208
          },
          {
            "type": "lora",
            "weight": 0.65,
            "modelVersionId": 249861
          },
          {
            "type": "lora",
            "weight": 0.6,
            "modelVersionId": 258687
          },
          {
            "type": "lora",
            "weight": 0.8,
            "modelVersionId": 332071
          },
          {
            "type": "lora",
            "weight": 0.9,
            "modelVersionId": 413566
          },
          {
            "type": "lora",
            "weight": 0.6,
            "modelVersionId": 421757
          },
          {
            "type": "lora",
            "weight": 0.8,
            "modelVersionId": 426333
          }
        ]
      },
      "username": "Ajuro",
      "baseModel": "SDXL 1.0"
    }
  ],
  "metadata": {
    "nextCursor": "1|1712265847499",
    "nextPage": "https://civitai.com/api/v1/images?limit=1&cursor=1%7C1712265847499"
  }
}
```

## Установка

### Для установки на android
1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/IvanCRA/HomeWorkViewsTP2.git
   ``` 
   Если клонировали ранее, то не нужно.
2. Откройте проект в Android Studio.
3. Подождите пока Android Studio доустанавливает необходимые зависимости.
4. Синхронизируйте проект с Gradle(Sync Now).