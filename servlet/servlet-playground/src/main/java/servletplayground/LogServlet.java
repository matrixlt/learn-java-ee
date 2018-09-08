package servletplayground;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Logger;

@WebServlet(name = "LogServlet", urlPatterns = {"/"})
public class LogServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LogServlet.class.getName());

    @Inject
    private Counter counter;

    private static void increaseSessionCounter(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        synchronized (httpSession) {
            Integer logCounter = (Integer) httpSession.getAttribute("logCounter");
            if (logCounter == null) {
                httpSession.setAttribute("logCounter", 1);
            } else {
                httpSession.setAttribute("logCounter", logCounter + 1);
            }
        }
    }

    private static Integer getSessionCounter(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        synchronized (httpSession) {
            return (Integer) httpSession.getAttribute("logCounter");
        }
    }

    private void increaseContextCounter() {
        ServletContext servletContext = this.getServletContext();
        synchronized (servletContext) {
            Integer logCounter = (Integer) servletContext.getAttribute("logCounter");
            if (logCounter == null) {
                servletContext.setAttribute("logCounter", 1);
            } else {
                servletContext.setAttribute("logCounter", logCounter + 1);
            }
        }
    }

    private Integer getContextCounter() {
        ServletContext servletContext = this.getServletContext();
        synchronized (servletContext) {
            return (Integer) servletContext.getAttribute("logCounter");
        }
    }

    private void logRequestBack(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.increaseContextCounter();
        increaseSessionCounter(request);
        counter.increase();
        response.setContentType("text/plain");
        response.setBufferSize(8192);
        response.setStatus(200);

        try (PrintWriter writer = response.getWriter();
                BufferedReader reader = new BufferedReader(request.getReader())) {
            writer.println("Counter: " + this.getContextCounter());
            writer.println("Session counter: " + getSessionCounter(request));
            writer.println("ApplicationScoped counter: " + counter.getInteger());

            String method = request.getMethod();
            String path = request.getPathInfo();
            String query = request.getQueryString();
            writer.println("Method:\t" + method);
            writer.println("Path:\t" + path);
            writer.println("Query:\t" + query);

            writer.println("Headers:\t");
            Enumeration<String> headers = request.getHeaderNames();
            while (headers.hasMoreElements()) {
                String name = headers.nextElement();
                String value = request.getHeader(name);
                writer.println("\t" + name + ": " + value);
            }

            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length != 0) {
                writer.println("Cookies:");
                for (Cookie cookie : cookies) {
                    writer.println("\t" + cookie.getName() + ": " + cookie.getValue());
                    writer.println("\t\tcomment: " + cookie.getComment());
                    writer.println("\t\tdomain: " + cookie.getDomain());
                    writer.println("\t\tpath" + cookie.getPath());
                    writer.println("\t\tmaxAge: " + cookie.getMaxAge());
                    writer.println("\t\tsecure: " + cookie.getSecure());
                    writer.println("\t\tversion: " + cookie.getVersion());
                }
            }

            String line = reader.readLine();
            if (line != null) {
                writer.println("Content: ");
            }
            while (line != null) {
                writer.println(line);
                line = reader.readLine();
            }

            ServletContext servletContext = this.getServletContext();
            ServletConfig servletConfig = this.getServletConfig();
            writer.println("Servlet Info:");

            writer.println("Servlet name:");
            String servletName = servletConfig.getServletName();
            writer.println(servletName);

            Enumeration<String> attributes = servletContext.getAttributeNames();
            writer.println("Attributes:");
            synchronized (servletContext) {
                while (attributes.hasMoreElements()) {
                    String name = attributes.nextElement();
                    Object value = servletContext.getAttribute(name);
                    writer.println("\t" + name + ": " + value.toString());
                }
            }

            Enumeration<String> initParameters = servletConfig.getInitParameterNames();
            if (initParameters.hasMoreElements()) {
                writer.println("Init Parameters:");
            }
            while (initParameters.hasMoreElements()) {
                String name = initParameters.nextElement();
                String value = servletConfig.getInitParameter(name);
                writer.println("\t" + name + ": " + value);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.logRequestBack(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.logRequestBack(request, response);
    }

    @Override
    public String getServletInfo() {
        return "A servlet log all request";
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        logger.info("LogServlet service start");
        super.service(req, res);
        logger.info("LogServlet service stop");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("LogServlet init");
    }

    @Override
    public void destroy() {
        super.destroy();
        logger.info("LogServlet destroy");
    }
}
