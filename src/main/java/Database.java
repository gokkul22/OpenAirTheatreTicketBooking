import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Database
{

    /*public static void main( String[] args )
    {
        Database a = new Database();
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        String s = "mongodb+srv://Bharath:bharath12345678@movie.jeait.mongodb.net/Movie?retryWrites=true&w=majority";
        try(MongoClient mongoClient = MongoClients.create(s))
        {
            MongoCollection<Document> record = mongoClient.getDatabase("Movie").getCollection("userlogin");
            System.out.println(a.getObj(record,"bharath","12345"));
        }
    }*/
    public void insertuser(MongoCollection<Document> record,String s,String s2)
    {
        Document doc = new Document("email",s).append("password",s2);
        record.insertOne(doc);
    }
    public  boolean findregister(MongoCollection<Document> record,String s,String s2)
    {
        Document doc = record.find(Filters.eq("email", s)).first();
        if(doc==null )
            return true;
        else
            return false;

    }
    public  boolean findlogin(MongoCollection<Document> record,String s,String s2)
    {
        Document doc = record.find(Filters.eq("email", s)).first();
        if(doc!=null )
            if(doc.getString("password").equals(s2))
                return true;
            else
                return false;
        else
            return false;

    }
    public ObjectId getObj(MongoCollection<Document> record, String s, String s2)
    {
        Document doc = record.find(Filters.eq("email", s)).first();
        if(doc==null)
            return null;
        else {
            if (doc.getString("password").equals(s2))
                return doc.getObjectId("_id");
        }
        return null;
    }

    public void insertMovie(MongoCollection rec, String mo, String th, String ti, String id,int se) {
        Document doc = new Document("Id",id).append("Movie",mo).append("Theatre",th).append("Time",ti).append("Slots",se);
        rec.insertOne(doc);
    }

    public void insertTickets(MongoCollection rec, String id) {
        Document doc = new Document("Id",id);
        for (int i=0;i<4;i++)
            for (int j=0;j<10;j++)
                doc.append((char)(65+i)+""+(j+1),0);
        rec.insertOne(doc);
    }

    public boolean findmovie(MongoCollection<Document> rec, String mo, String th, String ti) {
        Document doc = rec.find(Filters.eq("Movie", mo)).first();
        if(doc==null)
            return true;
        else
            if(doc.getString("Theatre").equals(th) && doc.getString("Time").equals(ti))
                return false;
            else
                return true;
    }

    public Document findNo(MongoCollection<Document> rec, String no) {
        Document doc = rec.find(Filters.eq("Id", no)).first();
        if (doc==null)
            return null;
        else
            return doc;
    }
    public MongoIterable<String> getDate(MongoClient mongoClient) {
        MongoIterable<String> list = mongoClient.listDatabaseNames();
        return list;
    }


    public String findTheatre(MongoCollection<Document> rec) {
        FindIterable<Document> docs = rec.find();
        String s="";
        if (docs != null)
            for (Document doc : docs)
                s=s+doc.getString("Theatre")+"-";
        return  s;
    }

    public String findMovie(MongoCollection<Document> rec, String Theatre) {
        FindIterable<Document> docs = rec.find(Filters.eq("Theatre", Theatre));
        String s="";
        if (docs != null)
            for (Document doc : docs)
                s=s+doc.getString("Movie")+"-";
        return  s;
    }

    public String findDate(MongoCollection<Document> rec, String Theatre, String Movie) {
        FindIterable<Document> docs = rec.find(Filters.eq("Theatre", Theatre));
        String s="";
        if (docs != null)
            for (Document doc : docs)
                if (doc.getString("Movie").equals(Movie))
                    s=s+doc.getString("Time")+" ";
        return  s;
    }

    public String findId(MongoCollection<Document> rec, String Theatre, String Movie, String Time) {
        FindIterable<Document> docs = rec.find(Filters.eq("Theatre", Theatre));
        String s ="";
        for (Document doc : docs)
            if(doc.getString("Movie").equals(Movie) && doc.getString("Time").equals(Time)) {
                s = doc.getString("Id");
                break;
            }
        return s;
    }

    public int findticket(MongoCollection<Document> rec, String s,String Id) {
        Document doc = rec.find(Filters.eq("Id",Id)).first();
        try {
            return doc.getInteger(s);
        }
        catch (NullPointerException e){
            return 2;
        }
    }

    public void upTic(MongoCollection<Document> rec, String t, String id) {
        rec.updateOne(new Document("Id",id), Updates.set(t,1));
    }

    public void upSe(MongoCollection<Document> rec, int noticket, String id) {
        Document doc = rec.find(Filters.eq("Id",id)).first();
        rec.updateOne(doc, Updates.set("Slots",doc.getInteger("Slots")-noticket));
    }

    public void insertbill(MongoCollection<Document> rec, String em, String Theatre, String Movie, String Time, String slono, int tax) {
        Document doc = new Document("email",em).append("Theatre",Theatre).append("Movie",Movie).append("Time",Time).append("Slots",slono).append("Total Amount",tax);
        rec.insertOne(doc);
    }


    public void cancel(MongoClient mongoClient,String s,String em, String th, String mv, String ti, String tic, String ta) {
        MongoCollection<Document> rec1 = mongoClient.getDatabase(s).getCollection("MovieAdmin");
        String id = findId(rec1,th,mv,ti);
        Document doc1 = rec1.find(Filters.eq("Id",id)).first();
        rec1.updateOne(doc1, Updates.set("Slots",doc1.getInteger("Slots")+1));
        MongoCollection<Document> rec2 = mongoClient.getDatabase(s).getCollection("Tickets");
        rec2.updateOne(new Document("Id",id), Updates.set(tic,0));
        MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("UserBill");
        FindIterable<Document> docs = rec.find(Filters.eq("email",em));
        if (docs != null)
            for (Document doc : docs)
                if (doc.getString("Theatre").equals(th) && doc.getString("Movie").equals(mv) && doc.getString("Time").equals(ti) && doc.getString("Slots").equals(tic) && doc.getInteger("Total Amount").equals(Integer.parseInt(ta)) ) {
                    rec.deleteOne(doc);
                    break;
                }
    }

    public boolean findemail(MongoCollection<Document> record, String em1) {
        Document doc = record.find(Filters.eq("email", em1)).first();
        if (doc==null)
            return false;
        else
            return true;
    }

    public String findpass(MongoCollection<Document> record, String em1) {
        Document doc = record.find(Filters.eq("email", em1)).first();
        if (doc==null)
            return "";
        else
            return doc.getString("password");
    }

    public String findAll(MongoCollection<Document> rec) {
        FindIterable<Document> docs = rec.find();
        String s="";
        if (docs != null)
            for (Document doc : docs)
                s=s+doc.getString("Theatre")+"-"+doc.getString("Movie")+"-"+doc.getString("Time")+"\n";
        return  s;
    }

    public String findMovie1(MongoCollection<Document> rec) {
        FindIterable<Document> docs = rec.find();
        String s="";
        if (docs != null)
            for (Document doc : docs)
                s=s+doc.getString("Movie")+"-";
        return  s;
    }
}