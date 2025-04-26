
# Image System Service

## Overview
This project provides a simple service to manage and search images based on:
- **Image name** (exact search)
- **Tags** (single or multi-term search)
- **Date and time** (exact date or date range)

Each image has:
- A **unique name**
- A set of **tags**
- A **timestamp** (`LocalDateTime`)

---

## Features
- **Upload** an image.
- **Search** by:
  - **Exact name**
  - **Tag(s)**
  - **Multiple terms** (intersection search)
  - **Specific date**
  - **Date range**
- **Duplicate Name Protection**: Images must have unique names.

---

## How to Use

1. **Upload Image**

```java
Image image = new Image("beach_sunset", new HashSet<>(Arrays.asList("nature", "night", "vacation", "summer")), LocalDateTime.now());
imageService.uploadImage(image);
```

2. **Search by Exact Name**

```java
Set<Image> result = imageService.generalSearch("beach_sunset");
```

3. **Search by Tag**

```java
Set<Image> result = imageService.generalSearch("vacation");
```

4. **Multi-Term Search**

```java
Set<Image> result = imageService.generalSearch("vacation night");
```

5. **Search by Exact Date**

```java
Set<Image> result = imageService.generalSearch("2025-04-26T16:02:03.782421800");
```

6. **Search by Date Range**

```java
Set<Image> result = imageService.searchDateTimeRange(startDate, endDate);
```

---

## Code Structure

- `ImageService`
  - Main service managing upload and search operations.
  - Internally maintains:
    - `nameMap`: Fast exact-name lookup.
    - `textMap`: Tag-based and multi-term lookup.
    - `dateMap`: Date-time and range-based search.

- `Image`
  - Data class representing an image with name, tags, and dateTime.

- `DuplicateImageNameException`
  - Thrown when trying to upload an image with a name that already exists.

---

## Test Outputs

Here are outputs for basic operations done in the service:

### 📸 Uploading Images

```
Uploaded 4 images:
 - egypt
 - beach_sunset
 - mountain_view
 - city_night
```

---

### 🔍 Exact Name Search

**Query:** `"egypt"`

```
Results: 1
 - egypt
```

---

### 🔖 Tag Search

**Query:** `"vacation"`

```
Results: 3
 - beach_sunset
 - mountain_view
 - city_night
```

---

### 🔍 Multi-Term Search

**Query:** `"vacation night"`

```
Results: 2
 - beach_sunset
 - city_night
```

---

### 🕰️ Date Search

**Query:** `2025-04-25T16:32:03.782421800`

```
Results: 0
```

---

### 📆 Date Range Search

**Query:** from `2025-04-23T16:32:03.782421800` to `2025-04-26T19:32:03.782421800`

```
Results: 3
 - egypt (2025-04-26T13:32:03.782421800)
 - beach_sunset (2025-04-26T16:02:03.782421800)
 - city_night (2025-04-26T16:32:03.782421800)
```

---

### 🚫 Empty Search

**Query:** `""`

```
Results: 0
```

---

### ❓ Non-Existent Search

**Query:** `"non_existent"`

```
Results: 0
```

---

## Notes
- Searches are **case-insensitive** for tags.
- In multi-term search, **all terms must match** (intersection logic).
- Date parsing tries to match **ISO_LOCAL_DATE_TIME** format.
- Duplicate image names are **not allowed**.

---

## Example Image Data

| Name           | Tags                          | DateTime                         |
|----------------|-------------------------------|----------------------------------|
| egypt          | nature, pyramids, zoo         | 2025-04-26T13:32:03.782421800    |
| beach_sunset   | nature, night, vacation, summer| 2025-04-26T16:02:03.782421800    |
| mountain_view  | nature, vacation, landscape    | 2025-04-27T16:32:03.782421800    |
| city_night     | night, vacation, landscape     | 2025-04-26T16:32:03.782421800    |

---

## Author
- **Fawry Dev Task**
- Feel free to modify, enhance, and extend this project!
