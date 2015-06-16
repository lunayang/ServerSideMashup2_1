package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import store.Cart;
import store.Item;
import store.Catalog;

/**
 * Servlet implementation class XMLCartServlet
 * maintain a cart session
 * generate the response result by the urlString 
 * @author Luna Yang
 * @version 1.1
 * @since 02/06/15
 */
public class XMLCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public XMLCartServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    synchronized public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {
        // Bounce to post, for debugging use
        // Hit this servlet directly from the browser to see XML

         Enumeration headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = (String) headers.nextElement();
            System.out.println(header + ": " + req.getHeader(header));
        }
        //get the item parameter
//        String itemString = req.getParameter("item");
        //get the urlString parameter
        String urlAsString = req.getParameter("url");
        res.setContentType("text/html;charset=UTF-8");
        try {
           
            PrintWriter out = res.getWriter();
            ServletContext context = getServletContext();
            InputStream xsl = (InputStream) (context.getResourceAsStream("/XSLTTransformerCode.xsl"));
//
//            //get the item from the catalog based on the item parameter
//            Catalog catalog = new Catalog();
//            String urlAsString;
//            Item item = catalog.getItem(itemString);
//            //get the url of that item
//            urlAsString = item.getUrl();
            
            //generate the result
            Source xmlDoc = new StreamSource(urlAsString);
            Source xslDoc = new StreamSource(xsl);
            Result result = new StreamResult(out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer trans = factory.newTransformer(xslDoc);
            trans.transform(xmlDoc, result);

            //close the stream
            xsl.close();
            out.close();
            
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    synchronized protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        
        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = (String) headers.nextElement();
            System.out.println(header + ": " + request.getHeader(header));
        }
        //get the item parameter
        String itemString = request.getParameter("item");
        response.setContentType("text/html;charset=UTF-8");
        try {
           
            PrintWriter out = response.getWriter();
            ServletContext context = getServletContext();
            InputStream xsl = (InputStream) (context.getResourceAsStream("/XSLTTransformerCode.xsl"));

            //get the item from the catalog based on the item parameter
            Catalog catalog = new Catalog();
            String urlAsString;
            Item item = catalog.getItem(itemString);
            //get the url of that item
            urlAsString = item.getUrl();
            //generate the result
            Source xmlDoc = new StreamSource(urlAsString);
            Source xslDoc = new StreamSource(xsl);
            Result result = new StreamResult(out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer trans = factory.newTransformer(xslDoc);
            trans.transform(xmlDoc, result);

        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String readXML(String urlString) throws IOException {

        if (urlString == null) {
            return null;
        }

        InputStreamReader in = null;
        StringBuilder sb = new StringBuilder();

        URL url = new URL(urlString);
        URLConnection urlConn = url.openConnection();
        if (urlConn != null) {
            urlConn.setReadTimeout(60 * 1000);
        }
        if (urlConn != null && urlConn.getInputStream() != null) {
            in = new InputStreamReader(urlConn.getInputStream(),
                    Charset.defaultCharset());
            BufferedReader br = new BufferedReader(in);
            if (br != null) {
                int cp;
                while ((cp = br.read()) != -1) {
                    sb.append((char) cp);
                }
                br.close();
            }
        }
        in.close();

        return sb.toString();
    }

    public Cart getCartFromSession(HttpServletRequest req) {
        //if session is true
        HttpSession session = req.getSession(true);
        //get its attribute to cart
        Cart cart = (Cart) session.getAttribute("cart");
        //if cart is null, create new one and set the attributes
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        return cart;

    }
}
