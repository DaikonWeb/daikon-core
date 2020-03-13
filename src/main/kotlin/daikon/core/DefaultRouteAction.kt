package daikon.core

import daikon.core.HttpStatus.NOT_FOUND_404
import daikon.core.HttpStatus.OK_200
import daikon.core.Method.GET

class DefaultRouteAction : RouteAction {
    override fun handle(request: Request, response: Response, context: Context) {
        if (request.method() == GET && request.path() == "/") {
            response.status(OK_200)
            response.type("text/html")
            response.write(html())
        } else {
            response.status(NOT_FOUND_404)
        }
    }

    private fun html() = """
<!DOCTYPE html>
<html>
  <head><title>Welcome to Daikon!</title></head>
  <body style="padding:1px;text-align:center;">
    <div style="margin: 0 auto;padding: 1px;max-width:1024px;">
      <h1>You are eating a Daikon!!</h1>
      <svg style="width:300px;" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
        viewBox="0 0 512.001 512.001" style="enable-background:new 0 0 512.001 512.001;" xml:space="preserve">
      <path style="fill:#A5DC69;" d="M365.479,173.106l20.111,21.673L498.791,81.578c1.212-1.212,2.045-2.606,2.563-4.07l-8.628-2.653
        c-13.645-4.195-28.491-0.506-38.584,9.588L365.479,173.106z"/>
      <g>
        <path style="fill:#46C373;" d="M348.293,155.921l67.62-67.62c7.147-7.147,9.958-17.554,7.381-27.327l-3.792-14.382l-91.72,91.72
          L348.293,155.921z"/>
        <polygon style="fill:#46C373;" points="403.669,233.817 498.75,138.738 456.219,124.15 384.947,195.422 	"/>
      </g>
      <polygon style="fill:#FFFFFF;" points="273.876,104.024 330.634,47.268 346.553,83.143 309.446,120.25 "/>
      <path style="fill:#f9edcf;" d="M370.208,183.506c-19.552-19.551-46.503-29.852-74.11-28.326l0,0
        c-32.388,1.79-61.751,19.616-78.283,47.525l-92.983,156.973c-22.908,38.671-39.807,80.456-50.261,123.921
        c43.464-10.453,85.249-27.354,123.921-50.261l156.972-92.983c27.91-16.532,45.735-45.894,47.525-78.283l0,0
        c1.526-27.609-8.776-54.56-28.328-74.112L370.208,183.506z"/>
      <path d="M114.989,241.251c2.63,0,5.21-1.07,7.07-2.93c1.87-1.87,2.93-4.44,2.93-7.07c0-2.63-1.06-5.21-2.93-7.08
        c-1.86-1.86-4.44-2.92-7.07-2.92s-5.21,1.06-7.07,2.92c-1.86,1.87-2.93,4.44-2.93,7.08c0,2.63,1.07,5.21,2.93,7.07
        C109.779,240.181,112.359,241.251,114.989,241.251z"/>
      <path d="M203.188,333.81c2.64,0,5.21-1.07,7.07-2.93c1.86-1.86,2.93-4.44,2.93-7.07c0-2.63-1.07-5.21-2.93-7.07
        c-1.86-1.86-4.44-2.93-7.07-2.93c-2.63,0-5.21,1.07-7.07,2.93s-2.93,4.44-2.93,7.07c0,2.63,1.07,5.21,2.93,7.07
        C197.978,332.74,200.558,333.81,203.188,333.81z"/>
      <path d="M501.994,129.278l-27.373-9.388l31.242-31.242c5.234-5.234,7.293-12.647,5.508-19.831
        c-1.785-7.183-7.075-12.771-14.149-14.946L478.48,48.11c-15.987-4.915-33.166-1.281-45.744,9.449l-11.159-42.321
        c-0.913-3.462-3.606-6.171-7.062-7.105c-3.456-0.933-7.146,0.052-9.679,2.583L349.784,65.77l-10.01-22.558
        c-1.337-3.012-4.072-5.173-7.312-5.775c-3.24-0.604-6.569,0.429-8.9,2.76l-53.329,53.329c-9.32-2.025-18.949-2.823-28.645-2.287
        c-35.791,1.979-68.065,21.573-86.334,52.413l-27.608,46.606c-2.814,4.752-1.244,10.886,3.507,13.701
        c4.752,2.814,10.885,1.245,13.701-3.507l21.139-35.686l45.184,45.184c1.953,1.953,4.512,2.929,7.071,2.929
        c2.559,0,5.119-0.976,7.071-2.929c3.905-3.905,3.905-10.237,0-14.143l-48.544-48.544c15.328-21.272,39.438-34.59,65.917-36.054
        c24.747-1.371,48.972,7.896,66.49,25.415l58.409,58.408c17.076,17.076,26.304,40.535,25.492,64.62l-26.408-26.408
        c-3.905-3.905-10.237-3.905-14.143,0c-3.905,3.905-3.905,10.237,0,14.143l36.323,36.323c-6.473,19.928-19.989,37.084-38.488,48.042
        l-78.56,46.534l-36.335-36.334c-3.905-3.905-10.237-3.905-14.142,0s-3.905,10.237,0,14.142l32.714,32.713l-60.651,35.926
        c-30.05,17.8-62.372,31.975-95.892,42.194c-0.355-0.529-0.763-1.032-1.231-1.5l-15.6-15.601c-3.905-3.905-10.237-3.905-14.142,0
        s-3.906,10.237,0,14.142l9.041,9.042c-17.907,4.471-36.08,7.82-54.366,9.997c5.565-46.746,18.796-92.767,38.834-135.441
        l42.188,42.188c1.953,1.953,4.512,2.929,7.071,2.929c2.559,0,5.119-0.977,7.071-2.929c3.905-3.905,3.906-10.237,0-14.142
        l-46.908-46.908c3.202-6.042,6.536-12.011,10.017-17.887l20.452-34.526c2.814-4.752,1.244-10.886-3.507-13.701
        c-4.75-2.815-10.885-1.244-13.701,3.507l-20.452,34.526C27.709,358.973,6.191,425.511,0.042,493.047
        c-0.5,5.5,3.552,10.624,9.052,11.125c0.309,0.028,0.614,0.042,0.918,0.042c0.08,0,0.158-0.01,0.238-0.012
        c-0.001,0.004-0.001,0.008-0.002,0.012c0.304,0,0.61-0.014,0.918-0.042c67.537-6.15,134.076-27.669,192.422-62.23l69.567-41.207
        c1.611-0.445,3.133-1.294,4.399-2.56c0.032-0.032,0.058-0.067,0.089-0.099l82.917-49.115c30.841-18.269,50.435-50.543,52.413-86.335
        c0.424-7.676,0.013-15.313-1.181-22.788l94.028-94.029c2.439-2.44,3.449-5.965,2.671-9.327
        C507.712,133.121,505.257,130.397,501.994,129.278z M327.403,64.641l7.226,16.285l-27.964,27.964
        c-4.832-3.194-9.902-5.986-15.159-8.352L327.403,64.641z M322.328,121.51l84.347-84.347l6.95,26.36
        c1.663,6.306-0.168,13.087-4.777,17.7l-63.39,63.39l-22.133-22.133C322.997,122.152,322.66,121.834,322.328,121.51z M359.6,158.757
        l21.831-21.831c0.001-0.001,0.002-0.002,0.003-0.003l41.551-41.551c0.004-0.004,0.007-0.008,0.011-0.011l21.032-21.032
        c7.495-7.495,18.445-10.216,28.576-7.101l18.742,5.762c0.16,0.049,0.493,0.151,0.618,0.652c0.124,0.5-0.122,0.747-0.241,0.865
        L383.511,182.715c-0.586-0.615-1.177-1.225-1.779-1.827L359.6,158.757z M405.734,217.611c-2.605-6.651-5.867-13.047-9.758-19.076
        l62.901-62.901l21.47,7.364L405.734,217.611z"/>
      </svg>
    </div>
  </body>
</html>
"""

}
