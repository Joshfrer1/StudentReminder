package com.example.studentreminder.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentreminder.R;
import com.example.studentreminder.models.ToDoItem;
import com.example.studentreminder.models.User;
import com.example.studentreminder.models.UpcomingEvent;
import com.example.studentreminder.models.Course;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class USUCanvasAPI {
    public interface OnRequestCompleteListener<T> {
        public void onComplete(T[] result, String errorMessage);
    }

    public class
    InvalidTokenException extends RuntimeException {
        public InvalidTokenException() {
            super("Error: Canvas API token was invalid. Did you forgot to include you token in strings.xml");
        }
    }

    private RequestQueue queue;
    private String token;
    private static USUCanvasAPI instance;

    public static USUCanvasAPI getInstance(Context context) {
        if (instance == null) {
            instance = new USUCanvasAPI(context, context.getResources().getString(R.string.canvas_token));
        }
        return instance;
    }

    private USUCanvasAPI(Context context, String token) {
        if (token.equals("") || token == null) {
            throw new InvalidTokenException();
        }
        this.queue = Volley.newRequestQueue(context);
        this.token = token;
    }

    public void getUser(OnRequestCompleteListener<User> onRequestCompleteListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "https://usu.instructure.com/api/v1/users/self?access_token=" + token,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        User[] users = new User[1];
                        User user = new User();
                        try {
                            user.canvasId = (int)response.get("id");
                            user.name = response.getString("name");
                        } catch (JSONException e) {
                            onRequestCompleteListener.onComplete(null, e.toString());
                            e.printStackTrace();
                        }
                        System.out.println("RESULT: " + response.toString());
                        users[0] = user;
                        onRequestCompleteListener.onComplete(users, null);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                onRequestCompleteListener.onComplete(null, error.toString());
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void getUpcomingEvents(OnRequestCompleteListener<ToDoItem> onRequestCompleteListener) {
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                "https://usu.instructure.com/api/v1/users/self/upcoming_events?per_page=1&access_token=" + token,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ToDoItem[] events = new ToDoItem[response.length()];
                        for(int i=0; i<response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                ToDoItem item = new ToDoItem();
                                item.title = obj.getString("title");
                                item.canvasId = Integer.parseInt(obj.getString("id"));
                                item.dueDate = obj.getString("due_at");
                                events[i] = item;
                            } catch (JSONException e) {
                                onRequestCompleteListener.onComplete(null, e.toString());
                                e.printStackTrace();
                            }
                        }
                        onRequestCompleteListener.onComplete(events, null);
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                onRequestCompleteListener.onComplete(null, error.toString());
                error.printStackTrace();

            }
        });

        queue.add(jsonObjectRequest);
    }


}
