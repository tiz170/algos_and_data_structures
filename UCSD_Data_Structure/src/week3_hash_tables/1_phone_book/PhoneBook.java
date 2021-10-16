import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneBook {

    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    private List<Contact> contacts = new ArrayList<>();
    private String[] myContacts = new String[100000000];

    public static void main(String[] args) {
        // PhoneBook是{}全部代码的总class name；processQueries是一个function, 里面会call某个核心function从而得到output
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }

    // brute-force解法 (核心function)
    private void processQuery(Query query) {
        if (query.type.equals("add")) {
            // if we already have contact with such number,
            // we should rewrite contact's name
            boolean wasFound = false;
            for (Contact contact : contacts)
                if (contact.number == query.number) {
                    contact.name = query.name;
                    wasFound = true;
                    break;
                }
            // otherwise, just add it
            if (!wasFound)
                contacts.add(new Contact(query.name, query.number));
        }
        else if (query.type.equals("del")) {
            for (Iterator<Contact> it = contacts.iterator(); it.hasNext(); )
                if (it.next().number == query.number) {
                    it.remove();
                    break;
                }
        }
        else {
            String response = "not found";
            for (Contact contact: contacts)
                if (contact.number == query.number) {
                    response = contact.name;
                    break;
                }
            writeResponse(response);
        }
    }

    // fast解法 (新核心function)
    private void processQueryFast(Query query) {
        if (query.type.equals("add")) {
            myContacts[query.number] = query.name;

        }
        else if (query.type.equals("del")) {
            myContacts[query.number] = null;
        }
        else {
            String response = "not found";
            if(myContacts[query.number] != null) {
                response = myContacts[query.number];
            }
            writeResponse(response);
        }
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            // processQuery(readQuery());
            processQueryFast(readQuery());
    }

    // 当element type不止一种时 可建一个class将它们囊括
    static class Contact {
        String name;
        int number;

        // function
        public Contact(String name, int number) {
            // this. 的作用？
            this.name = name;
            this.number = number;
        }
    }

    // 当element type不止一种时 可建一个class将它们囊括
    static class Query {
        String type;
        String name;
        int number;

        // function
        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        // java里已经写好的直接用的class（br和st是自定义的名字）
        BufferedReader br;
        StringTokenizer st;

        // 以下都是固定写法, 每次都一样
        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
