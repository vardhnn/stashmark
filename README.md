# StashMark

A retro-styled Android bookmark manager. Paste any link — website, GitHub repo,
YouTube video, article — and the app fetches the title, description and image
automatically, then saves it as a card in a masonry grid.

Built with a single-activity Jetpack Compose UI, no Room/kapt dependencies:
bookmarks persist to a JSON file in app-private storage.

## Features

- **Paste-to-stash**: tap `+`, paste a link, hit Fetch — metadata fills in automatically
- **Smart resolvers**:
  - YouTube → oEmbed (exact title, author, `hqdefault` thumbnail)
  - GitHub → REST v3 (description, ★ stars, language, owner avatar)
  - Everything else → OpenGraph / Twitter-card / `<title>` scrape
- **Collections**: All · Link · Repo · Video · Read, with retro color-coded chips
- **Full-text search** across titles, descriptions, URLs and kinds
- **Share-in**: share any link from Chrome, YouTube, GitHub etc. and StashMark opens prefilled
- **Detail sheet**: open in browser, edit, share, delete
- **Retro design system**: `#D0553B` primary, `#E5883A` secondary, `#2E798A` tertiary,
  `#F1F1F1` background, Space Grotesk headlines, Hanken Grotesk body, Space Mono labels
  (all fonts bundled as variable fonts), dark-mode variant included

## Build

Requirements: JDK 17+ (Android Studio's JBR works), Android SDK 37, internet for
first dependency sync.

```bash
./gradlew assembleDebug        # debug APK  -> app/build/outputs/apk/debug/
./gradlew assembleRelease      # shrunk APK (~1.9 MB), signed with debug key
```

Install on a device:

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Project layout

```
app/src/main/java/com/stashmark/app/
├── MainActivity.kt          # single activity: header, search, filters, grid, sheets
├── data/
│   ├── Bookmark.kt          # model + LinkKind
│   ├── BookmarkStore.kt     # JSON file persistence
│   └── LinkResolver.kt      # URL normalization + metadata fetchers
└── ui/
    ├── theme/Theme.kt       # palette, variable-font typography, shapes
    ├── components/          # KindChip, StashButton, DateStamp, EmptyState
    ├── BookmarkCard.kt      # masonry card
    ├── AddEditSheet.kt      # add (with fetch) / edit sheet
    ├── DetailSheet.kt       # preview + open/edit/share/delete
    └── LibraryViewModel.kt  # state holder
```

## Toolchain notes

- AGP 9.4.0 with built-in Kotlin, upgraded to KGP 2.4.10 via buildscript classpath
  (required to read Kotlin 2.4 metadata from current Compose/Coil artifacts)
- Gradle 9.6.0 wrapper, JDK 25-compatible
- Compose BOM 2026.09.00, Coil 3 (network via OkHttp), Jsoup, kotlinx.serialization
