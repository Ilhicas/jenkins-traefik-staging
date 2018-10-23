import responder
import graphene

class Query(graphene.ObjectType):
    hello = graphene.String(name=graphene.String(default_value="stranger"))
    def resolve_hello(self, info, name):
        return f"Hello {name}"

api = responder.API()
api.add_route("/graph", graphene.Schema(query=Query))

@api.route("/{greeting}")
async def greet_world(req, resp, *, greeting):
    resp.text = f"{greeting}, world!"


if __name__ == '__main__':
    api.run(address="0.0.0.0" , port=5000)