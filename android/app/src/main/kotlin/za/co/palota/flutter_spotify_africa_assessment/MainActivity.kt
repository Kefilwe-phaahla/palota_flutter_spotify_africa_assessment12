package za.co.palota.flutter_spotify_africa_assessment

import io.flutter.embedding.android.FlutterActivity

class MainActivity: FlutterActivity() {
    public boolean addPlaylist(String pname) {


    Uri playlists = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
    Cursor c = resolver.query(playlists, new String[] { "*" }, null, null,
            null);
    long playlistId = 0;
    c.moveToFirst();
    do {
        String plname = c.getString(c
                .getColumnIndex(MediaStore.Audio.Playlists.NAME));
        if (plname.equalsIgnoreCase(pname)) {
            playlistId = c.getLong(c
                    .getColumnIndex(MediaStore.Audio.Playlists._ID));
            break;
        }
    } while (c.moveToNext());
    c.close();

    if (playlistId != 0) {
        Uri deleteUri = ContentUris.withAppendedId(playlists, playlistId);
        Log.d(TAG, "REMOVING Existing Playlist: " + playlistId);

        // delete the playlist
        resolver.delete(deleteUri, null, null);
    }

    Log.d(TAG, "CREATING PLAYLIST: " + pname);
    ContentValues v1 = new ContentValues();
    v1.put(MediaStore.Audio.Playlists.NAME, pname);
    v1.put(MediaStore.Audio.Playlists.DATE_MODIFIED,
            System.currentTimeMillis());
    Uri newpl = resolver.insert(playlists, v1);
    Log.d(TAG, "Added PlayLIst: " + newpl);

    flag=true;
    return flag;
}

    
}

